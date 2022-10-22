package ru.oktemsec.game100ykt.data

import android.app.Activity
import android.content.res.TypedArray
import android.util.Log
import androidx.fragment.app.Fragment
import ru.oktemsec.game100ykt.R
import ru.oktemsec.game100ykt.models.InformationCard
import ru.oktemsec.game100ykt.utils.ArrayRandom

class Game(activity: Activity) {
    var informationList: Array<String>
    var imageList: TypedArray

    var dynamicNumberCardList: MutableList<Int>

    init {
        informationList = activity.resources.getStringArray(R.array.information_texts)
        imageList = activity.resources.obtainTypedArray(R.array.information_drawables)

        dynamicNumberCardList = ArrayRandom().getRandomArray(imageList.length()).toMutableList()
    }

    fun generateInformationCard() : InformationCard {

        val randomNumber = dynamicNumberCardList[0]

        val before = dynamicNumberCardList
        printList(before)

        dynamicNumberCardList.removeAt(0)
        val after = dynamicNumberCardList
        printList(after)

        return InformationCard(
            image = imageList.getResourceId(randomNumber, 0),
            informationText = informationList[randomNumber]
        )
    }

    private fun printList(list: MutableList<Int>) {
        var result = ""
        for (i in 0 until list.size){
            result = result + " " + list[i].toString()
        }
        Log.d("brearey", result)
    }
}