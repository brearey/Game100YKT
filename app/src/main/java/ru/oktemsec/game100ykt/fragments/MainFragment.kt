package ru.oktemsec.game100ykt.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.oktemsec.game100ykt.data.GameViewModel
import ru.oktemsec.game100ykt.databinding.FragmentMainBinding
import ru.oktemsec.game100ykt.dialogs.ChallengeDialogFragment
import ru.oktemsec.game100ykt.dialogs.QuestionDialogFragment
import ru.oktemsec.game100ykt.models.Question
import ru.oktemsec.game100ykt.utils.navigator


class MainFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentMainBinding.inflate(inflater, container, false).apply {

        // click color buttons
        blueButton.setOnClickListener {
            showQuestionDialogFragment(reward = 1)
        }

        whiteButton.setOnClickListener {
            showChallengeDialogFragment(0, false)
        }

        greenButton.setOnClickListener {
            showChallengeDialogFragment(1, false)
        }

        yellowButton.setOnClickListener {
            showChallengeDialogFragment(2, false)
        }

        brownButton.setOnClickListener {
            showQuestionDialogFragment(reward = 2)
        }

        purpleButton.setOnClickListener {
            showChallengeDialogFragment(1, true)
        }

        pinkButton.setOnClickListener {
            showChallengeDialogFragment(1, true)
        }

        redButton.setOnClickListener {
            onRedButtonClicked()
        }

        // click help button
        helpButton.setOnClickListener {
            onHelpPressed()
        }

    }.root

    private fun onHelpPressed() {
        navigator().showHelpScreen()
    }

    private fun onRedButtonClicked() {
        navigator().showInformationCard()
    }

    private fun showQuestionDialogFragment(reward: Int) {
        navigator().showQuestionDialog(reward)
    }

    private fun showChallengeDialogFragment(challengeNumber: Int, isTable: Boolean) {
        // challengeNumber = 0 Передай право хода следующему игроку
        // challengeNumber = 1 Переходи по картинке-мосту на другой квадратик
        // challengeNumber = 2 Таймаут. Пропускаешь ход

        val challengeDialogFragment = ChallengeDialogFragment()
        challengeDialogFragment.show(parentFragmentManager, challengeNumber, isTable)
    }
}