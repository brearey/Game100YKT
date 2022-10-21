package ru.oktemsec.game100ykt.dialogs

import android.app.Dialog
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import ru.oktemsec.game100ykt.R
import kotlin.random.Random

class ChallengeDialogFragment: DialogFragment() {

    private val dialog_argument: Int
        get() = requireArguments().getInt(DIALOG_ARGUMENT)

    private val isTable: Boolean
        get() = requireArguments().getBoolean(DIALOG_IS_TABLE)

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

        // разделение табличных заданий от обычных
        val message = if (isTable) {
            tableChallengeList[Random.nextInt(0, tableChallengeList.size)]
        }
        else {
            challengeList[dialog_argument]
        }

        val alertDialogBuilder = AlertDialog.Builder(requireContext())
            .setView(view)
            .setCancelable(true)
            .setIcon(R.drawable.ic_challenge)
            .setTitle("Ваше задание")
            .setMessage(message)
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
        @JvmStatic val DIALOG_IS_TABLE = "DIALOG_IS_TABLE"

        //for debug
        @JvmStatic val challengeList = listOf(
            "Передай право хода следующему игроку",
            "Переходи по картинке-мосту на другой квадратик",
            "Таймаут. Пропускаешь ход"
        )

        //for debug
        @JvmStatic val tableChallengeList = listOf(
            "Прочитай любое стихотворение",
            "Расскажи любой факт о декларации, который ты успел узнать",
            "Спой песню",
            "Сделай комплимент рядом сидящему игроку",
            "Передай ход следующему"
        )

        //for debug
        @JvmStatic val imageList = listOf(
            R.drawable.game_area,
            null,
            R.drawable.ykt100years
        )
    }

    fun show(manager: FragmentManager, dialog_argument: Int, isTable: Boolean) {
        val dialogFragment = ChallengeDialogFragment()
        dialogFragment.arguments = bundleOf(
            DIALOG_ARGUMENT to dialog_argument,
            DIALOG_IS_TABLE to isTable
        )
        dialogFragment.show(manager, TAG)
    }
}