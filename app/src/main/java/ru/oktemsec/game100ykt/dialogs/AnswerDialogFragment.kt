package ru.oktemsec.game100ykt.dialogs

import android.app.Dialog
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import ru.oktemsec.game100ykt.R

class AnswerDialogFragment: DialogFragment() {

    private val dialog_argument: Int
        get() = requireArguments().getInt(DIALOG_ARGUMENT)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view = activity?.layoutInflater?.inflate(R.layout.image_layout, null)
        val image = view?.findViewById<ImageView>(R.id.dialog_imageview)

        // set image of question or answer
        if ( imageList[ dialog_argument ] != null ) {
            imageList[ dialog_argument ]?.let { image?.setImageResource(it) }
        }
        else {
            // clear image
            image?.setImageResource(0)
        }


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

        //for debug
        @JvmStatic val answersList = listOf(
            "Слепцов",
            "Семён Андревич Новгородов",
            "Всего 4:\n2011 — самый большой ансамбль хомусистов в мире с 1344 участниками;\n2012 — самый большой круговой танец в мире — осуохай, в котором участвовали 15 293 человека;\n2014 — рекордное количество участников в массовом обряде благословения Алгыс — 11 136 человек;\n2017 — мировой рекорд по самому большому количеству людей в национальной одежде — 16 626 участников."
        )

        //for debug
        @JvmStatic val imageList = listOf(
            R.drawable.game_area,
            null,
            R.drawable.ykt100years
        )
    }

    fun show(manager: FragmentManager, dialog_argument: Int) {
        val dialogFragment = AnswerDialogFragment()
        dialogFragment.arguments = bundleOf(DIALOG_ARGUMENT to dialog_argument)
        dialogFragment.show(manager, TAG)
    }
}