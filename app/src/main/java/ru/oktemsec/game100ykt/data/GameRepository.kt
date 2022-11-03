package ru.oktemsec.game100ykt.data

import ru.oktemsec.game100ykt.R
import ru.oktemsec.game100ykt.models.InformationCard
import ru.oktemsec.game100ykt.models.Question

class GameRepository {
    // Информационные карточки
    private val informations_array: Array<InformationCard> = arrayOf(
        InformationCard(0, "Конституция Республики Саха (Якутия) - основной закон РС(Я). Принята Верховным Советом Республики Саха (Якутия) 4 апреля 1992 г. Изменения и дополнения вносились республиканскими законами от 26 января 1994 г., от 20 апреля 1994 г., от 7 июля 2000 г., от 15 июня 2001 г., от 17 и 18 июля 2001 г., от 28 января 2002 г., от 6 марта 2002 г., от 29 апреля 2002 г., 10 июля 2003 г., 25 апреля 2006 г."),
        InformationCard(R.drawable.barahov, "Исидор Никифорович Барахов (Иванов) (31 января (12 февраля) 1898 – 15 сентября 1938) - советский государственный, общественно-политический деятель Якутии начала XX века, участник гражданской войны. Вместе с Максимом Аммосовым и Платоном Ойунским стоял у истоков создания Якутской АССР в составе РСФСР."),
        InformationCard(R.drawable.gerb, "Герб Республики Саха (Якутия) (якут. Саха Ороспуубулукэтин дьаралыга) является государственным символом Республики Саха (Якутия). Принят Парламентом Республики 26 декабря 1992 года. Зарегистрирован за № 182 в Государственном геральдическом регистре Российской Федерации. Авторский коллектив: Афанасий Николаевич Осипов, Василий Семенович Парников, Владимир Никифорович Игнатьев, Иннокентий Афанасьевич Потапов."),
        InformationCard(0, "Гимн Республики Саха (Якутия) (якут. Саха Ороспуубулукэтин орогойун ырыата) - один из главных государственных символов Якутии, наряду с флагом и гербом. Принят Законом Республики Саха (Якутия) от 15 июля 2004 года №313-III \"Об официальной символике Республики Саха (Якутия)\". Заменил первоначальный гимн, который существовал с 1991 по 2004 гг. Слова на якутском языке написаны Саввой Ивановичем Тарасовым и Михаилом Елисеевичем Тимофеевым. Перевод текста на русский язык осуществлен Владимиром Николаевичем Фёдоровым. Автором музыки является Герасимов Кирилл Афанасьевич.")
    )

    // Вопросы
    private val questions_array: Array<Question> = arrayOf(
        Question(
            "Все мы знаем якутского писателя, основоположника якутской советской литературы, общественного деятеля Платона Ойунского. А какая его настоящая фамилия?",
            R.drawable.game_area,
            "Слепцов",
            0
        ),
        Question(
            "Кто создал первый вариант якутского алфавита?",
            0,
            "Семён Андревич Новгородов",
            0
        ),
        Question(
            "Все вы знаете, что во время проведения Ысыаха Туймаады было установлено несколько мировых рекордов Гиннесса. А сколько их было?",
            R.drawable.square100ykt,
            "Всего 4:\n2011 — самый большой ансамбль хомусистов в мире с 1344 участниками;\n2012 — самый большой круговой танец в мире — осуохай, в котором участвовали 15 293 человека;\n2014 — рекордное количество участников в массовом обряде благословения Алгыс — 11 136 человек;\n2017 — мировой рекорд по самому большому количеству людей в национальной одежде — 16 626 участников.",
            0
        ),
    )

    // Задания
    private val challengeList = listOf(
        "Передай право хода следующему игроку",
        "Переходи по картинке-мосту на другой квадратик",
        "Таймаут. Пропускаешь ход"
    )

    private val tableChallengeList = listOf(
        "Прочитай любое стихотворение",
        "Расскажи любой факт о декларации, который ты успел узнать",
        "Спой песню"
    )

    private val magnetoChallengeList = listOf(
        "Вы попали в магнитный поток. Иди на два шага назад",
        "Вы попали в магнитный поток. Иди на три шага вперед",
        "Вы попали в магнитный поток. Тебе повезло бери жетон"
    )

    // Методы информационные карточки
    fun getTextsList() : Array<String> {
        var result = emptyArray<String>()
        informations_array.forEachIndexed { index, _ ->
            result += informations_array[index].informationText
        }
        return result
    }
    fun getImagesList() : Array<Int>{
        var result = emptyArray<Int>()
        informations_array.forEachIndexed { index, _ ->
            result += informations_array[index].image
        }
        return result
    }


    // Методы вопросы
    fun getQuestionTextsList(): Array<String> {
        var result = emptyArray<String>()
        questions_array.forEachIndexed { index, _ ->
            result += questions_array[index].question
        }
        return result
    }
    fun getQuestionImagesList() : Array<Int> {
        var result = emptyArray<Int>()
        questions_array.forEachIndexed { index, _ ->
            result += questions_array[index].image
        }
        return result
    }
    fun getQuestionAnswersList() : Array<String> {
        var result = emptyArray<String>()
        questions_array.forEachIndexed { index, _ ->
            result += questions_array[index].answer
        }
        return result
    }

    // Методы задания
    fun getChallengesList(): List<String> {
        return challengeList
    }
    fun getTableChallengesList(): List<String> {
        return tableChallengeList
    }

    //Meridian
    fun getMagnetoChallengeList(): List<String> {
        return magnetoChallengeList
    }
}