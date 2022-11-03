package ru.oktemsec.game100ykt.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import ru.oktemsec.game100ykt.R


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        try {
            val videoHolder = findViewById<VideoView>(R.id.videoView)
            val video: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.logo_splash_video)
            videoHolder.setVideoURI(video)
            videoHolder.setOnCompletionListener { jump() }
            videoHolder.start()
            
        } catch (ex: Exception) {
            jump()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        jump()
        return true
    }

    private fun jump() {
        if (isFinishing) return
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}