package ru.oktemsec.game100ykt.dialogs

import android.app.Dialog
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import ru.oktemsec.game100ykt.R
import ru.oktemsec.game100ykt.data.GameRepository
import ru.oktemsec.game100ykt.fragments.PlayerFragment
import ru.oktemsec.game100ykt.utils.navigator
import kotlin.random.Random

class ChallengeDialogFragment: DialogFragment() {

    private val dialog_argument: Int
        get() = requireArguments().getInt(DIALOG_ARGUMENT)

    private val isTable: Boolean
        get() = requireArguments().getBoolean(DIALOG_IS_TABLE)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view = activity?.layoutInflater?.inflate(R.layout.image_layout, null)
        val image = view?.findViewById<ImageView>(R.id.dialog_imageview)

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
        }
        // Если задание стих
        else if (randomChallenge == 0 && isTable && dialog_argument == 1) {
            neutralButtonText = R.string.open_verse
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
}