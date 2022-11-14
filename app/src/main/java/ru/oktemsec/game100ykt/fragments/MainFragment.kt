package ru.oktemsec.game100ykt.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import ru.oktemsec.game100ykt.R
import ru.oktemsec.game100ykt.databinding.FragmentMainBinding
import ru.oktemsec.game100ykt.dialogs.ChallengeDialogFragment
import ru.oktemsec.game100ykt.utils.ChildMessage
import ru.oktemsec.game100ykt.utils.navigator


class MainFragment: Fragment() {

    private lateinit var bergenImageView: ImageView

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
            animateChild()
            playSoundOo()
            showChallengeDialogFragment(0, false)
        }

        greenButton.setOnClickListener {
            showChallengeDialogFragment(1, false)
        }

        yellowButton.setOnClickListener {
            animateChild()
            playSoundOo()
            showChallengeDialogFragment(2, false)
        }

        brownButton.setOnClickListener {
            showQuestionDialogFragment(reward = 2)
        }

        purpleButton.setOnClickListener {
            showChallengeDialogFragment(1, true)
        }

        pinkButton.setOnClickListener {
            showChallengeDialogFragment(2, true)
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

    private fun animateChild() {
        val bergenImageView = activity?.findViewById<ImageView>(R.id.bergen)
        if (bergenImageView != null) {
            ChildMessage().startAnimateWithEnd(requireContext(), R.animator.oo_start_animator, R.animator.oo_end_animator, bergenImageView)
        }
    }

    private fun playSoundOo() {
        ChildMessage().playMessageSound(requireContext(), R.raw.oo)
    }
}