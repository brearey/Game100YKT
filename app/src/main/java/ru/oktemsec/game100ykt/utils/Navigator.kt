package ru.oktemsec.game100ykt.utils

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

typealias ResultListener<T> = (T) -> Unit

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    // открыть Информационную карточку
    fun showInformationCard()

    // открыть Правила игры
    fun showHelpScreen()

    // открыть песню
    fun showPlayerScreen()

    // открыть стихотворение
    fun showVerseScreen()

    fun showQuestionDialog(reward: Int)

    fun goBack()

    fun goToMenu()

    fun <T : Parcelable> publishResult(result: T)

    fun <T : Parcelable> listenResult(clazz: Class<T>, owner: LifecycleOwner, listener: ResultListener<T>)

}