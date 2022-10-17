package ru.oktemsec.game100ykt.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.oktemsec.game100ykt.databinding.FragmentMainBinding
import ru.oktemsec.game100ykt.dialogs.QuestionDialogFragment
import ru.oktemsec.game100ykt.utils.navigator
import kotlin.random.Random


class MainFragment: Fragment() {

    // for debug
    private val TAG = "brearey"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentMainBinding.inflate(inflater, container, false).apply {

        // click color buttons
        blueButton.setOnClickListener {
            showChallengeDialogFragment()
        }

        // click help button
        helpButton.setOnClickListener {
            onHelpPressed()
        }

    }.root

    private fun onHelpPressed() {
        navigator().showHelpScreen()
    }

    private fun showChallengeDialogFragment() {
        val questionDialogFragment = QuestionDialogFragment()
        questionDialogFragment.show(parentFragmentManager, Random.nextInt(0, QuestionDialogFragment.questionsList.size))
    }
}