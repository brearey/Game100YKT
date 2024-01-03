package ru.oktemsec.game100ykt.dialogs

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import ru.oktemsec.game100ykt.R
import ru.oktemsec.game100ykt.data.GameRepository
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.Exception

class AnswerDialogFragment: DialogFragment() {

    private val dialog_argument: Int
        get() = requireArguments().getInt(DIALOG_ARGUMENT)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view = activity?.layoutInflater?.inflate(R.layout.image_layout, null)
        val image = view?.findViewById<ImageView>(R.id.dialog_imageview)

        // Lists of data
        val answersList = GameRepository().getQuestionAnswersList()
        val imagesList = GameRepository().getQuestionImagesList()

        // Set image of answer
        if (imagesList[dialog_argument] == 0) {
            image?.layoutParams?.width = 0
            image?.layoutParams?.height = 0
        }
        image?.setImageResource(imagesList[dialog_argument])


        val alertDialogBuilder = AlertDialog.Builder(requireContext())
            .setView(view)
            .setCancelable(true)
            .setIcon(R.drawable.ic_challenge)
            .setTitle("Правильный ответ:")
            .setMessage(answersList[dialog_argument])
            .setPositiveButton(R.string.right) {_, _ ->
                calculateRatingElo(dialog_argument, 1);
                dialog?.cancel()
            }
            .setNegativeButton(R.string.wrong) {_, _ ->
                calculateRatingElo(dialog_argument, -1)
                readFile();
                dialog?.cancel()
            }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.setCanceledOnTouchOutside(false)
        return alertDialog
    }

    companion object {
        @JvmStatic val TAG = AnswerDialogFragment::class.java.simpleName
        @JvmStatic val DIALOG_ARGUMENT = "DIALOG_ARGUMENT"
    }

    fun show(manager: FragmentManager, dialog_argument: Int) {
        val dialogFragment = AnswerDialogFragment()
        dialogFragment.arguments = bundleOf(DIALOG_ARGUMENT to dialog_argument)
        dialogFragment.show(manager, TAG)
    }

    private fun writeToFile(content: String) {
        val fileName = "file.txt";
        val path: File? = activity?.filesDir;
        Log.d("brearey", path.toString());
        try {
            val writer: FileOutputStream = FileOutputStream(File(path, fileName));
            writer.write(content.toByteArray());
            writer.close();
        } catch (e: Exception) {
            e.printStackTrace();
        }
    }

    private fun readFile(): String {
        val fileName = "file.txt";
        val path: File? = activity?.filesDir;
        val file = File(path, fileName)
        return FileInputStream(file).bufferedReader().use { it.readText() }
    }

    private fun calculateRatingElo(index: Int, rating: Int) {
        val list = readFile().split(",").toMutableList()
        list[index] = (list[index].toInt() + rating).toString()
        var str = list[0]
        for (i in 0 until list.size - 1) {
            str += ",${list[i]}"
        }
        writeToFile(str)
    }
}