package ru.oktemsec.game100ykt.dialogs

import android.app.Dialog
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import ru.oktemsec.game100ykt.R
import ru.oktemsec.game100ykt.data.GameRepository

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
            .setNeutralButton(R.string.close_dialog) {_, _ ->
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
}