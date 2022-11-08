package ru.oktemsec.game100ykt.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.oktemsec.game100ykt.databinding.FragmentVerseBinding
import ru.oktemsec.game100ykt.utils.navigator

class VerseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentVerseBinding.inflate(inflater, container, false).apply {
        buttonBack.setOnClickListener { onBackPressed() }
    }.root

    private fun onBackPressed() {
        navigator().goBack()
    }

}