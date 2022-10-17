package ru.oktemsec.game100ykt.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import ru.oktemsec.game100ykt.R

class ChallengeDialogFragment: DialogFragment() {

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
            .setTitle("Задание № ${dialog_argument.toString()}")
            .setMessage(challengeList[dialog_argument])
            .setNeutralButton(R.string.close_dialog) {_, _ ->
                dialog?.cancel()
            }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.setCanceledOnTouchOutside(false)
        return alertDialog
    }

    companion object {
        @JvmStatic val TAG = ChallengeDialogFragment::class.java.simpleName

        @JvmStatic val DIALOG_ARGUMENT = "DIALOG_ARGUMENT"

        //for debug
        @JvmStatic val challengeList = listOf(
            "Передай право хода следующему игроку",
            "Переходи по картинке-мосту на другой квадратик",
            "Таймаут. Пропускаешь ход"
        )

        //for debug
        @JvmStatic val imageList = listOf(
            R.drawable.game_area,
            null,
            R.drawable.ykt100years
        )
    }

    fun show(manager: FragmentManager, dialog_argument: Int) {
        val dialogFragment = ChallengeDialogFragment()
        dialogFragment.arguments = bundleOf(DIALOG_ARGUMENT to dialog_argument)
        dialogFragment.show(manager, TAG)
    }
}