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
import ru.oktemsec.game100ykt.databinding.FragmentInformationCardBinding
import ru.oktemsec.game100ykt.utils.navigator
import kotlin.random.Random

class InformationCardFragment : Fragment() {

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
        val scale = requireContext().resources.displayMetrics.density
        binding.informationCardImageFront.cameraDistance = 8000 * scale
        binding.informationCardImageBack.cameraDistance = 8000 * scale
        // Animators
        front_anim = AnimatorInflater.loadAnimator(requireContext(), R.animator.front_animator) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(requireContext(), R.animator.back_animator) as AnimatorSet
        // Animate on click
        binding.informationCardImageBack.setOnClickListener { animateCard() }

        // Generate for an information card
        generateInformation()

        // Back button click
        binding.buttonBack.setOnClickListener { onBackPressed() }

        return binding.root
    }

    private fun generateInformation() {
        val randomInformaionNumber = Random.nextInt(0, imageList.size)
        // set image
        if ( imageList[ randomInformaionNumber ] != null ) {
            imageList[ randomInformaionNumber ]?.let { binding.icardImage.setImageResource(it) }
        }
        else {
            // clear image
            binding.icardImage.setImageResource(0)
        }

        // set text
        binding.icardText.text = informationList[randomInformaionNumber]

        //set source
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

        //for debug
        @JvmStatic val informationList = listOf(
            "Конституция Республики Саха (Якутия) - основной закон РС(Я). Принята Верховным Советом Республики Саха (Якутия) 4 апреля 1992 г. Изменения и дополнения вносились республиканскими законами от 26 января 1994 г., от 20 апреля 1994 г., от 7 июля 2000 г., от 15 июня 2001 г., от 17 и 18 июля 2001 г., от 28 января 2002 г., от 6 марта 2002 г., от 29 апреля 2002 г., 10 июля 2003 г., 25 апреля 2006 г.",
            "Исидор Никифорович Барахов (Иванов) (31 января (12 февраля) 1898 - 15 сентября 1938) - советский государственный, общественно-политический деятель Якутии начала XX века, участник гражданской войны. Вместе с Максимом Аммосовым и Платоном Ойунским стоял у истоков создания Якутской АССР в составе РСФСР.",
            "Герб Республики Саха (Якутия) (якут. Саха Ороспуубулукэтин дьаралыга) является государственным символом Республики Саха (Якутия). Принят Парламентом Республики 26 декабря 1992 года. Зарегистрирован за № 182 в Государственном геральдическом регистре Российской Федерации. Авторский коллектив: Афанасий Николаевич Осипов, Василий Семенович Парников, Владимир Никифорович Игнатьев, Иннокентий Афанасьевич Потапов.",
            "Гимн Республики Саха (Якутия) (якут. Саха Ороспуубулукэтин орогойун ырыата) - один из главных государственных символов Якутии, наряду с флагом и гербом. Принят Законом Республики Саха (Якутия) от 15 июля 2004 года №313-III \"Об официальной символике Республики Саха (Якутия)\". Заменил первоначальный гимн, который существовал с 1991 по 2004 гг. Слова на якутском языке написаны Саввой Ивановичем Тарасовым и Михаилом Елисеевичем Тимофеевым. Перевод текста на русский язык осуществлен Владимиром Николаевичем Фёдоровым. Автором музыки является Герасимов Кирилл Афанасьевич."
        )

        //for debug
        @JvmStatic val imageList = listOf(
            null,
            R.drawable.barahov,
            R.drawable.gerb,
            null,
        )
    }

}