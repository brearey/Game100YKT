package ru.oktemsec.game100ykt.models

import android.media.Image

data class Question(
    val question: String,
    val image: Image?,
    val answer: String
)
