package ru.oktemsec.game100ykt.dialogs

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.app.Dialog
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.animation.doOnEnd
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import ru.oktemsec.game100ykt.R
import ru.oktemsec.game100ykt.data.GameRepository
import ru.oktemsec.game100ykt.utils.navigator
import kotlin.random.Random

class ChallengeDialogFragment: DialogFragment() {

    private val dialog_argument: Int
        get() = requireArguments().getInt(DIALOG_ARGUMENT)

    private val isTable: Boolean
        get() = requireArguments().getBoolean(DIALOG_IS_TABLE)

    private lateinit var oo_start_animator: AnimatorSet
    private lateinit var oo_end_animator: AnimatorSet
    private lateinit var bergenImageView: ImageView

    private lateinit var mp: MediaPlayer

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view = activity?.layoutInflater?.inflate(R.layout.image_layout, null)
        val image = view?.findViewById<ImageView>(R.id.dialog_imageview)

        oo_start_animator = AnimatorInflater.loadAnimator(requireContext(), R.animator.oo_start_animator) as AnimatorSet
        oo_end_animator = AnimatorInflater.loadAnimator(requireContext(), R.animator.oo_end_animator) as AnimatorSet
        bergenImageView = requireActivity().findViewById(R.id.bergen)

        oo_start_animator.doOnEnd {
            oo_end_animator.setTarget(bergenImageView)
            oo_end_animator.start()
        }

        mp = MediaPlayer.create(requireContext(), R.raw.oo)

        // game repo
        val gameRepository = GameRepository()

        // set image of challenge
        image?.setImageResource(0)
        image?.layoutParams?.width = 0
        image?.layoutParams?.height = 0

        // разделение табличных заданий от обычных
        val randomChallenge = Random.nextInt(0, gameRepository.getTableChallengesList().size)
        val message = if (isTable) {
            when(dialog_argument) {
                1 -> gameRepository.getTableChallengesList()[randomChallenge]
                2 -> gameRepository.getMagnetoChallengeList()[Random.nextInt(0, gameRepository.getMagnetoChallengeList().size)]
                else -> "Произошла неизвестная ошибка"
            }
        }
        else {
            gameRepository.getChallengesList()[dialog_argument]
        }

        // Если задание песня
        val neutralButtonText:Int
        if (randomChallenge == 2 && isTable && dialog_argument == 1) {
            neutralButtonText = R.string.open_sing
            animateChild()
            playSoundOo()
        }
        // Если задание стих
        else if (randomChallenge == 0 && isTable && dialog_argument == 1) {
            neutralButtonText = R.string.open_verse
            animateChild()
            playSoundOo()
        }
        else {
            neutralButtonText = R.string.close_dialog
        }

        val alertDialogBuilder = AlertDialog.Builder(requireContext())
            .setView(view)
            .setCancelable(true)
            .setIcon(R.drawable.ic_challenge)
            .setTitle("Ваше задание")
            .setMessage(message)
            .setNeutralButton(neutralButtonText) {_, _ ->
                if (randomChallenge == 2 && isTable && dialog_argument == 1) {
                    // открыть песню
                    navigator().showPlayerScreen()

                } else if (randomChallenge == 0 && isTable && dialog_argument == 1) {
                    // открыть стих
                    navigator().showVerseScreen()
                }
                else {
                    dialog?.cancel()
                }
            }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.setCanceledOnTouchOutside(false)
        return alertDialog
    }

    override fun onDestroy() {
        super.onDestroy()
        mp.stop()
        mp.release()
    }

    companion object {
        @JvmStatic val TAG = ChallengeDialogFragment::class.java.simpleName

        @JvmStatic val DIALOG_ARGUMENT = "DIALOG_ARGUMENT"
        @JvmStatic val DIALOG_IS_TABLE = "DIALOG_IS_TABLE"

    }

    fun show(manager: FragmentManager, dialog_argument: Int, isTable: Boolean) {
        val dialogFragment = ChallengeDialogFragment()
        dialogFragment.arguments = bundleOf(
            DIALOG_ARGUMENT to dialog_argument,
            DIALOG_IS_TABLE to isTable
        )
        dialogFragment.show(manager, TAG)
    }

    private fun animateChild() {
        oo_start_animator.setTarget(bergenImageView)
        oo_start_animator.start()
    }

    private fun playSoundOo() {
        mp.setVolume(0.1f, 0.1f)
        mp.start()
    }
}