package ru.oktemsec.game100ykt.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import ru.oktemsec.game100ykt.R
import ru.oktemsec.game100ykt.databinding.FragmentPlayerBinding
import ru.oktemsec.game100ykt.utils.navigator
import java.util.concurrent.TimeUnit


class PlayerFragment : Fragment() {

    private lateinit var mediaPlayer: MediaPlayer
    private val handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentPlayerBinding.inflate(inflater, container, false).apply {
        buttonBack.setOnClickListener { onBackPressed() }

        // Media player
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.gimn_sakha)

        //Make sure you update Seekbar on UI thread
        //requireActivity().runOnUiThread(object : Runnable {
        val runnable: Runnable = object: Runnable {
            override fun run() {
                seekBar.progress = mediaPlayer.currentPosition
                handler.postDelayed(this, 500)
            }
        }
        // Duration
        val duration = mediaPlayer.duration.toLong()
        val sDuration: String = convertFormat(duration)
        playerDuration.text = sDuration

        btPlay.setOnClickListener {
            btPlay.visibility = View.GONE
            btPause.visibility = View.VISIBLE
            mediaPlayer.start()
            seekBar.max = mediaPlayer.duration
            handler.postDelayed(runnable, 0)
        }

        btPause.setOnClickListener {
            btPause.visibility = View.GONE
            btPlay.visibility = View.VISIBLE
            mediaPlayer.pause()
            handler.removeCallbacks(runnable)
        }

        btFf.setOnClickListener {
            var currentPosition = mediaPlayer.currentPosition.toLong()
            val duration = mediaPlayer.duration.toLong()

            if (mediaPlayer.isPlaying && duration != currentPosition) {
                currentPosition = currentPosition + 5000
                playerPosition.text = convertFormat(currentPosition)
                mediaPlayer.seekTo(currentPosition.toInt())
            }
        }

        btRew.setOnClickListener {
            var currentPosition = mediaPlayer.currentPosition.toLong()
            if (mediaPlayer.isPlaying && currentPosition > 5000) {
                currentPosition = currentPosition - 5000
                playerPosition.text = convertFormat(currentPosition)
                mediaPlayer.seekTo(currentPosition.toInt())
            }
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
                playerPosition.text = convertFormat(mediaPlayer.currentPosition.toLong())
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {    }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {    }
        })

        mediaPlayer.setOnCompletionListener {
            btPause.visibility = View.GONE
            btPlay.visibility = View.VISIBLE
            mediaPlayer.seekTo(0)
        }
    }.root

    private fun convertFormat(duration: Long): String {
        return String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(duration),
            TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))
        )
    }

    private fun onBackPressed() {
        mediaPlayer.stop()
        navigator().goBack()
    }

}