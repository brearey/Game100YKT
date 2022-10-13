package ru.oktemsec.game100ykt.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.oktemsec.game100ykt.R
import ru.oktemsec.game100ykt.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, MainFragment.newInstance()).commit()
        }
    }
}