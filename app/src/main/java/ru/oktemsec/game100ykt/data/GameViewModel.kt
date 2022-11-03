package ru.oktemsec.game100ykt.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.oktemsec.game100ykt.models.InformationCard
import ru.oktemsec.game100ykt.models.Question
import ru.oktemsec.game100ykt.utils.ArrayRandom

class GameViewModel : ViewModel() {
    private val informationList = GameRepository().getTextsList()
    private val imageList = GameRepository().getImagesList()

    private val questionList = GameRepository().getQuestionTextsList()
    private val questionImageList = GameRepository().getQuestionImagesList()
    private val questionAnswerList = GameRepository().getQuestionAnswersList()

    // список ИК
    val randomNumberListForInformationCard: MutableLiveData<MutableList<Int>> by lazy {
        MutableLiveData<MutableList<Int>>()
    }

    // список доступных вопросов
    val availableQuestionsList: MutableLiveData<MutableList<Int>> by lazy {
        MutableLiveData<MutableList<Int>>()
    }



    init {
        // пока ИК и вопросы не связаны
        availableQuestionsList.value = ArrayRandom().getRandomArray( GameRepository().getQuestionTextsList().size ).toMutableList()
        randomNumberListForInformationCard.value = ArrayRandom().getRandomArray(GameRepository().getTextsList().size).toMutableList()

        //debug IC
        Log.d("brearey", GameRepository().getTextsList()[1])
    }

    fun nextInformationCard() : InformationCard {

        val randomNumber = randomNumberListForInformationCard.value?.first() ?: 0

        val before = randomNumberListForInformationCard.value
        printList("before IK", before)

        randomNumberListForInformationCard.value?.removeAt(0)
        val after = randomNumberListForInformationCard.value
        printList("after IK", after)

        return InformationCard(image = imageList[randomNumber], informationText = informationList[randomNumber])
    }

    fun nextQuestion() : Question {
        val randomNumber = availableQuestionsList.value?.first() ?: 0

        val before = availableQuestionsList.value
        printList("before questions", before)

        availableQuestionsList.value?.removeAt(0)
        val after = availableQuestionsList.value
        printList("after questions", after)

        return Question(question = questionList[randomNumber], image = questionImageList[randomNumber], answer = questionAnswerList[randomNumber], randomNumber)
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