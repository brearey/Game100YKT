package ru.oktemsec.game100ykt.utils

import java.util.Random

class ArrayRandom {
    fun getRandomArray(size: Int) :IntArray {
        var i: Int
        var j: Int
        val arraySize = size
        val randomSize = size
        val newRandom = Random()
        val array = IntArray(arraySize)
        i = 0
        while (i < array.size) {
            val randomNumber: Int = newRandom.nextInt(randomSize)
            j = 0
            while (j < i) {
                if (array[j] == randomNumber) {
                    break
                }
                j++
            }
            if (j == i) {
                array[i] = randomNumber
                i++
            }
        }

        return array
    }
}