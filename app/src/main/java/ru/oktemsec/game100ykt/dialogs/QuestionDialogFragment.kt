package ru.oktemsec.game100ykt.dialogs

import android.app.Dialog
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import ru.oktemsec.game100ykt.R
import ru.oktemsec.game100ykt.data.GameViewModel

class QuestionDialogFragment: DialogFragment() {

    private val gameViewModel : GameViewModel by activityViewModels()

    private val dialog_reward: Int
        get() = requireArguments().getInt(DIALOG_REWARD)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view = activity?.layoutInflater?.inflate(R.layout.image_layout, null)
        val image = view?.findViewById<ImageView>(R.id.dialog_imageview)

        // Question init
        val questionModel = gameViewModel.nextQuestion()
        // set image of question or answer
        image?.setImageResource(questionModel.image)


        val alertDialogBuilder = AlertDialog.Builder(requireContext())
            .setView(view)
            .setCancelable(false)
            .setIcon(R.drawable.ic_question)
            .setTitle("Вопрос:")
            .setMessage(
                questionModel.question + "\n\n" +
                "Ваша награда: Фишка х" + dialog_reward
            )
            .setNeutralButton(R.string.show_answer) {_, _ ->

                val answerDialogFragment = AnswerDialogFragment()
                answerDialogFragment.show(parentFragmentManager, questionModel.number)

            }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.setCanceledOnTouchOutside(false)
        return alertDialog
    }

    companion object {
        @JvmStatic val TAG = QuestionDialogFragment::class.java.simpleName
        @JvmStatic val DIALOG_REWARD = "DIALOG_REWARD"
    }

    fun show(manager: FragmentManager, reward: Int) {
        val dialogFragment = QuestionDialogFragment()
        dialogFragment.arguments = bundleOf(
            DIALOG_REWARD to reward
        )
        dialogFragment.show(manager, TAG)
    }
}