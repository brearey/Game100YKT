package ru.oktemsec.game100ykt.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.oktemsec.game100ykt.models.InformationCard
import ru.oktemsec.game100ykt.utils.ArrayRandom

class GameViewModel : ViewModel() {
    private val informationList = GameRepository().getTextsList()
    private val imageList = GameRepository().getImagesList()

    private val randomNumberListForInformationCard: MutableLiveData<MutableList<Int>> by lazy {
        MutableLiveData<MutableList<Int>>()
    }

    init {
        randomNumberListForInformationCard.value = ArrayRandom().getRandomArray(GameRepository().getTextsList().size).toMutableList()
    }

    fun nextInformationCard() : InformationCard {

        val randomNumber = randomNumberListForInformationCard.value?.first() ?: 0

        val before = randomNumberListForInformationCard.value
        printList("before", before)

        randomNumberListForInformationCard.value?.removeAt(0)
        val after = randomNumberListForInformationCard.value
        printList("after", after)

        return InformationCard(image = imageList[randomNumber], informationText = informationList[randomNumber])
    }

    fun itemsLeft() : Int {
        return randomNumberListForInformationCard.value?.size ?: 0
    }

    private fun printList(name: String, list: MutableList<Int>?) {
        var result = ""
        if (list != null) {
            for (i in 0 until list.size){
                result = result + " " + list?.get(i).toString()
            }
        }
        Log.d("brearey", "$name: $result")
    }
}