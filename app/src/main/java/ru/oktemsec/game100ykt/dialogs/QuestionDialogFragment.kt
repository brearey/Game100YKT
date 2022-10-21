package ru.oktemsec.game100ykt.dialogs

import android.app.Dialog
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import ru.oktemsec.game100ykt.R

class QuestionDialogFragment: DialogFragment() {

    private val dialog_argument: Int
        get() = requireArguments().getInt(DIALOG_ARGUMENT)
    private val dialog_reward: String?
        get() = requireArguments().getString(DIALOG_REWARD)

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
            .setCancelable(false)
            .setIcon(R.drawable.ic_question)
            .setTitle("Вопрос № ${dialog_argument.toString()}")
            .setMessage(
                questionsList[dialog_argument] + "\n\n" +
                "Ваша награда: " + dialog_reward
            )
            .setNeutralButton(R.string.show_answer) {_, _ ->

                val answerDialogFragment = AnswerDialogFragment()
                answerDialogFragment.show(parentFragmentManager, dialog_argument)

            }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.setCanceledOnTouchOutside(false)
        return alertDialog
    }

    companion object {
        @JvmStatic val TAG = QuestionDialogFragment::class.java.simpleName

        @JvmStatic val DIALOG_ARGUMENT = "DIALOG_ARGUMENT"
        @JvmStatic val DIALOG_REWARD = "DIALOG_REWARD"

        //for debug
        @JvmStatic val questionsList = listOf(
            "Все мы знаем якутского писателя, основоположника якутской советской литературы, общественного деятеля Платона Ойунского. А какая его настоящая фамилия?",
            "Кто создал первый вариант якутского алфавита?",
            "Все вы знаете, что во время проведения Ысыаха Туймаады было установлено несколько мировых рекордов Гиннесса. А сколько их было?"
        )

        //for debug
        @JvmStatic val imageList = listOf(
            R.drawable.game_area,
            null,
            R.drawable.ykt100years
        )
    }

    fun show(manager: FragmentManager, dialog_argument: Int, reward: String) {
        val dialogFragment = QuestionDialogFragment()
        dialogFragment.arguments = bundleOf(
            DIALOG_ARGUMENT to dialog_argument,
            DIALOG_REWARD to reward
        )
        dialogFragment.show(manager, TAG)
    }
}