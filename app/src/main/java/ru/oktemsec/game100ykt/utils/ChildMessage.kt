package ru.oktemsec.game100ykt.utils

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.media.MediaPlayer
import android.view.View
import androidx.core.animation.doOnEnd

class ChildMessage {

    fun startAnimateWithEnd(context: Context, animatorStart: Int, animatorEnd:Int, target: View) {
        val start_animator = AnimatorInflater.loadAnimator(context, animatorStart) as AnimatorSet
        val end_animator = AnimatorInflater.loadAnimator(context, animatorEnd) as AnimatorSet

        start_animator.setTarget(target)
        end_animator.setTarget(target)
        start_animator.start()

        start_animator.doOnEnd {
            end_animator.start()
        }
    }

    fun playMessageSound(context: Context, sound:Int) {
        val mp = MediaPlayer.create(context, sound)
        mp.setVolume(0.1f, 0.1f)
        mp.start()

        mp.setOnCompletionListener {
            it.stop()
            it.release()
        }
    }
}