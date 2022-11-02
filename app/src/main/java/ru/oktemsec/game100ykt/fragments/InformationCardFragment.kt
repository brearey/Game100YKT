package ru.oktemsec.game100ykt.fragments

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_information_card.*
import ru.oktemsec.game100ykt.R
import ru.oktemsec.game100ykt.data.GameViewModel
import ru.oktemsec.game100ykt.databinding.FragmentInformationCardBinding
import ru.oktemsec.game100ykt.utils.navigator

class InformationCardFragment(private val gameViewModel: GameViewModel) : Fragment() {

    private lateinit var front_anim: AnimatorSet
    private lateinit var back_anim: AnimatorSet
    private var isFront = true

    private lateinit var binding: FragmentInformationCardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInformationCardBinding.inflate(inflater, container, false)

        // Scale for an information card animation
        binding.informationCardImageFront.cameraDistance = 8000 * requireContext().resources.displayMetrics.density
        binding.informationCardImageBack.cameraDistance = 8000 * requireContext().resources.displayMetrics.density
        // Animators
        front_anim = AnimatorInflater.loadAnimator(requireContext(), R.animator.front_animator) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(requireContext(), R.animator.back_animator) as AnimatorSet
        // Animate on click
        binding.informationCardImageBack.setOnClickListener { animateCard() }

        // Back button click
        binding.buttonBack.setOnClickListener { onBackPressed() }

        // set information card content
        val informationCard = gameViewModel.nextInformationCard()
        binding.icardImage.setImageResource(informationCard.image)
        binding.icardText.text = informationCard.informationText


        return binding.root
    }

    private fun onBackPressed() {
        navigator().goBack()
    }

    private fun animateCard() {
        if (!isFront) {
            front_anim.setTarget(informationCardImageFront)
            back_anim.setTarget(informationCardImageBack)
            front_anim.start()
            back_anim.start()
            isFront = true
        }
        else {
            front_anim.setTarget(informationCardImageBack)
            back_anim.setTarget(informationCardImageFront)
            back_anim.start()
            front_anim.start()
            isFront = false
        }
    }

    companion object {
        @JvmStatic val TAG = InformationCardFragment::class.java.simpleName

    }

}