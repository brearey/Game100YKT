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
import ru.oktemsec.game100ykt.databinding.FragmentHelpBinding
import ru.oktemsec.game100ykt.databinding.FragmentInformationCardBinding
import ru.oktemsec.game100ykt.utils.navigator

class InformationCardFragment : Fragment() {

    lateinit var front_anim: AnimatorSet
    lateinit var back_anim: AnimatorSet
    var isFront = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentInformationCardBinding.inflate(inflater, container, false).apply {

        buttonBack.setOnClickListener { onBackPressed() }

        val scale = requireContext().resources.displayMetrics.density
        informationCardImageFront.cameraDistance = 8000 * scale
        informationCardImageBack.cameraDistance = 8000 * scale

        front_anim = AnimatorInflater.loadAnimator(requireContext(), R.animator.front_animator) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(requireContext(), R.animator.back_animator) as AnimatorSet

        informationCardImageBack.setOnClickListener {
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
    }.root

    private fun onBackPressed() {
        navigator().goBack()
    }

}