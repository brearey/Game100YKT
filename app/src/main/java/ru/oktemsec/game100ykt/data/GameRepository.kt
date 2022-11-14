package ru.oktemsec.game100ykt.data

import ru.oktemsec.game100ykt.R
import ru.oktemsec.game100ykt.models.InformationCard
import ru.oktemsec.game100ykt.models.Question

class GameRepository {
    // Информационные карточки
    private val informations_array: Array<InformationCard> = arrayOf(
        InformationCard(0, "Идеи самоуправления якутов возникли в XVII в. со времени вхождения «Ленской землицы» в состав Российского государства. Принципы создания улусной администрации из коренного населения, облегчения и упорядочения ряда повинностей населения озвучили перед монархами России отважные сыны народа саха Мазары Бозеков, Софрон Сыранов и Алексей Аржаков. Они четко и грамотно сформулировали свои требования, настойчиво добивались их положительного решения, не боясь за свою жизнь."),
        InformationCard(0, "«Письмо к интеллигенции» - обращение к якутской интеллигенции Алексея Елисеевича Кулаковского, великого якутского поэта и мыслителя, по вопросу спасения якутского народа как этноса. Письмо было написано 1912 г. в селе Качикатцы Хангаласского улуса."),
        InformationCard(0, "В марте-апреле 1917 г. якутской национальной интеллигенцией был создан союз «Свобода», летом этого же года преобразованный в национальную партию Якутский трудовой союз федералистов (ЯТСФ). Главной задачей партии было политическое развитие народа, организация и консолидация всех политических сил."),
        InformationCard(0, "В 1927 году Платон Ойунский написал провидческую, глубоко философскую работу «Великий столетний план» («Сүүс сыллаах улуу былаан») в котором, он представил прообраз Якутии в виде поэтапного плана, расписанного по десятилетиям до 2027 года."),
        InformationCard(0, "8 октября 1925 года над г. Якутск впервые поднялся аэроплан «Сопвич». Самолет взлетел с пристани «Даркылах» и совершил посадку на Зеленом лугу."),
        InformationCard(0, "Факты из истории открытия первой кимберлитовой трубки. В 1950 году Центральная экспедиция геологического управления начинает изучение проб песка из Якутии на предмет выявления минералов-спутников алмаза под руководством ленинградского геолога Наталии Сарсадских. Она разработала метод пиропового поиска алмазных месторождений. На поиски трубки в Якутию были отправлены геолог Лариса Попугаева с  помощником Фёдором Беликовым. Кимберлитовую трубку они открыли 21 августа 1954 года по методу поиска коренных алмазных месторождений по пиропам."),
        InformationCard(0, "Язык народа - живительный источник. Первый словарь якутского языка был составлен Эдуардом Карловичем Пекарским, выдающимся русским лингвистом. До Октябрьской революции было издано 5 выпусков Словаря. Его издание приостанавливалось, но продолжилось с 1925 г. и в 1930 г. вышел его последний 13-й выпуск."),
        InformationCard(0, "Якутский научный центр, комплекс научных учреждений СО РАН в Якутии, был организован в 1949 г. как Якутский филиал Академии Наук СССР и стал одним из первых научных учреждений на Севере. Сегодня это крупнейшее на северо-востоке РФ комплексное научно-исследовательское учреждение, функционирующее как региональный научный центр СО РАН."),
        InformationCard(0, "Строительство первого в мире газопровода на вечной мерзлоте успешно завершилось в 1967 г. 31 октября впервые газ поступил на промышленные и жилые объекты г. Якутска. К концу года вся первая очередь газопровода Таас Тумус - Якутск - Покровск протяженностью в 410 км. была сдана в эксплуатацию. Валовая добыча газа в 1970 г. составила уже 184 млн. м3."),
        InformationCard(0, "Институт мерзлотоведения в г. Якутск носит имя Павла Ивановича Мельникова, советского ученого-геолога, Героя Социалистического труда, заслуженного деятеля науки и техники РСФСР. Институт организован в 1960 году. Основными направлениями научной деятельности является исследование эволюции криолитозоны под влиянием природных и антропогенных факторов. При институте находится подземная лаборатория, криохранилище, музей. На территории института расположено Федеральное криохранилище семян растений - российский аналог Всемирного семенохранилища на Шпицбергене; хранилище было открыто 12 декабря 2012 г."),
        InformationCard(0, "В 1962 году создан Институт космических исследований и аэрономии СО АН СССР распоряжением Совета Министров РСФСР. Организатором и первым директором Института был Юрий Георгиевич Шафер. Постановлением Президиума РАН от 21 января 2003 г. № 22 Институту присвоено имя Шафера. Основные научные направления: астрофизика космических лучей, солнечно-земная физика."),
        InformationCard(0, "1976 год – можно смело назвать триумфом якутской школы борьбы. Воспитанники Заслуженного тренера ЯАССР, РСФСР, СССР Дмитрия Петровича Коркина покорили вершину Олимпа – Павел Пинигин стал чемпионом ХХI летних Олимпийских игр в Монреале, Роман Дмитриев и Александр Иванов – серебряными призерами."),
        InformationCard(0, "27 - сакральное число для народа саха. \n" +
                                                "27 апреля 1922 г. образована Якутская Автономная Советская Социалистическая Республика (ЯАССР). \n" +
                                                "27 сентября 1990 г.  принята Декларация о государственном суверенитете Якутской - Саха ССР.  \n" +
                                                "27 апреля 1992 г. \tпринята современная Конституция Республики Саха (Якутия).\n"),
        InformationCard(0, "Международные спортивные игры «Дети Азии» проводятся каждые четыре года с 1996 года. Игры были организованы в ознаменование 100-летия современного Олимпийского движения по инициативе первого президента Республики Саха (Якутия) Михаила Николаева."),
        InformationCard(0, "Всемирный Музей мамонта был создан в 1991 году по инициативе  Петра Алексеевича Лазарева, первого якутского мамонтолога, как научный и культурный центр по изучению мамонтовой фауны, среды ее обитания и пропаганды научных знаний. В 1995 году музей включён в состав Академии Наук РС(Я) и стал называться Музеем мамонта."),
        InformationCard(0, "11 января 1991 г. Президиум Верховного Совета республики принял постановление об объявлении конкурса на создание государственных символов республики - конкурс на лучший проект флага, герба и гимна. Конкурс длился почти два года. Только за первые три месяца работы в конкурсную комиссию поступило 53 проекта государственного флага."),
        InformationCard(0, "Гимн Республики Саха (Якутия) является региональным гимном Республики Саха, субъекта Российской Федерации. Является одним из официальных символов Республики Саха, наряду с флагом и гербом Республики Саха. Первоначально он был написан на языке саха Саввой Тарасовым и Михаилом Тимофеевым. Гимн переведён на русский язык Владимиром Фёдоровым. Музыку написал Кирилл Герасимов. Гимн официально принят 15 июля 2004 года."),
        InformationCard(0, "Олонхо́ (якут. Олоҥхо) — древнейшее эпическое искусство якутов. Занимает центральное место в системе якутского фольклора. Термин «Олонхо» обозначает как эпическую традицию в целом, так и название отдельных сказаний. В 2009 году ЮНЕСКО включило олонхо в свой список «нематериального наследия человечества»."),
        InformationCard(0, "Национальный парк «Ле́нские столбы́» находится в Хангаласском улусе Якутии в 104 км от города Покровска. Ленские столбы представляют собой тянущийся на многие километры комплекс вертикально вытянутых скал, причудливо громоздящихся вдоль берега Лены, глубокой долиной, прорезающей Приленское плато. Объект всемирного наследия ЮНЕСКО с 2012 года."),
    )

    // Вопросы
    private val questions_array: Array<Question> = arrayOf(
        Question(
            "Укажите трех выдающихся сынов народа саха, которые первыми отстаивали права якутов на государственном уровне перед монархами России.\n" +
                    "1. Василий Никифоров (Күлүмнүүр)\n" +
                    "2. Алексей Аржаков (Сэhэн Ардьакыап)\n" +
                    "3. Мазары Бозеков\n" +
                    "4. Софрон Сыранов (Дьокутаат)\n" +
                    "5. Ника Мымахов\n",
            0,
            "2. Алексей Аржаков (Сэhэн Ардьакыап),\n3. Мазары Бозеков,\n4. Софрон Сыранов (Дьокутаат)",
            0
        ),
        Question(
            "Назовите год написания именитого труда А.Е. Кулаковского «Письмо к якутской интеллигенции».",
            0,
            "1912 г.",
            0
        ),
        Question(
            "Летом 1917 г. усилиями якутской интеллигенции была создана партия, главной задачей которой было политическое развитие народа, организация и консолидация политических сил. Как называлась эта партия?\n" +
            "1. Союз «Свобода», преобразованный в Якутский трудовой союз федералистов (ЯТСФ)\n" +
            "2. Союз «Пролетарии всех стран, объединяйтесь!»\n" +
            "3. КПСС (Коммунистическая партия Советского Союза)\n",
            0,
            "1. Союз «Свобода», преобразованный в Якутский трудовой союз федералистов (ЯТСФ)",
            0
        ),
        Question(
            "Какой выдающийся представитель якутской интеллигенции поставил вопрос об образовании автономной Якутской республики в составе РСФСР перед бюро Российской Коммунистической Партии большевиков?",
            0,
            "Платон Алексеевич Ойунский",
            0
        ),
        Question(
            "Известно, что Ойунский – это псевдоним великого якутского писателя. Назовите настоящую фамилию Платона Алексеевича.",
            0,
            "Слепцов",
            0
        ),
        Question(
            "Как называется произведение Платона Ойунского, в котором он описал этапы развития Якутии?",
            0,
            "Великий столетний план. Сүүс сыллаах улуу былаан.",
            0
        ),
        Question(
            "Нестор Каландаришвили, революционный деятель, выехав на ликвидацию отрядов якутских повстанцев во время гражданской войны, попал в засаду и был убит. Назовите место гибели легендарного героя Гражданской войны.",
            0,
            "За деревней Техтюр на Хахсытской протоке",
            0
        ),
        Question(
            "Назовите трех близких соратников П.А. Ойунского.",
            0,
            "Аммосов Максим Кирович, Аржаков Степан Максимович, Барахов Исидор Никифорович",
            0
        ),
        Question(
            "В каком году была принята первая Конституция ЯАССР?",
            0,
            "1925 г.",
            0
        ),
        Question(
            "Чье имя носит Саха академический театр?",
            0,
            "П.А. Ойунского",
            0
        ),
        Question(
            "Первый аэроплан «Сопвич» взлетел с пристани «Даркылах» и совершил посадку на территории: \n" +
                    "1. Зеленого луга\n" +
                    "2. Магана\n" +
                    "3. Табаги\n",
            0,
            "1. Зеленого луга",
            0
        ),
        Question(
            "Когда началось изучение кимберлитовой трубки в Якутии?",
            0,
            "1954 г.",
            0
        ),
        Question(
            "Назовите автора-составителя первого словаря якутского языка.",
            0,
            "Эдуард Карлович Пекарский",
            0
        ),
        Question(
            "Назовите год начала якутского радиовещания.",
            0,
            "1930 г.",
            0
        ),
        Question(
            "Как называется одно из первых научных учреждений на Севере, образованное в 1949 г. как Якутский филиал Академии Наук СССР?",
            0,
            "Якутский научный центр",
            0
        ),
        Question(
            "В каком году успешно завершилось строительство первого в мире газопровода на вечной мерзлоте?",
            0,
            "1967 г.",
            0
        ),
        Question(
            "Что такое криохранилище?\n" +
                    "1. Подземная лаборатория \n" +
                    "2. Специализированные камеры с жидким азотом\n" +
                    "3. Музей\n",
            0,
            "2. Специализированные камеры с жидким азотом",
            0
        ),
        Question(
            "Чье имя носит Институт космических исследований и аэрономии СО АН СССР?",
            0,
            "Юрий Георгиевич Шафер",
            0
        ),
        Question(
            "Назовите имя знаменитого якутского тренера, воспитавшего первых олимпийских чемпионов из народа саха.",
            0,
            "Дмитрий Петрович Коркин",
            0
        ),
        Question(
            "Почему число «27» считается сакральным для народа саха?",
            0,
            "27 апреля 1922г. образована Якутская Автономная Советская Социалистическая Республика (ЯАССР).\n" +
                    "27 сентября 1990г. принята Декларация о государственном суверенитете Якутской - Саха ССР.\n" +
                    "27 апреля 1992г. принята современная Конституция Республики Саха (Якутия).\n",
            0
        ),
        Question(
            "Назовите дату провозглашения Декларации о государственном суверенитете, которая ознаменовала начало нового этапа развития государственности Якутии.",
            0,
            "27 сентября 1990 г.",
            0
        ),
        Question(
            "В 1996 году в ознаменование 100-летия современного Олимпийского движения по инициативе первого президента Республики Саха (Якутия) Михаила Николаева были организованы…",
            0,
            "Международные спортивные игры «Дети Азии»",
            0
        ),
        Question(
            "По чьей инициативе в 1991 году был создан Всемирный Музей мамонта?",
            0,
            "Петра Алексеевича Лазарева, первого якутского мамонтолога",
            0
        ),
        Question(
            "Что обозначают цвета флага РС(Я)?",
            R.drawable.flag_sakha,
            "Голубой цвет символизирует честь, верность, искренность и ясное мирное небо, является символом свободы и мира, красный – цвет силы, мужества, белый – цвет чистоты, мудрости, радости, зеленый цвет олицетворяет свободу и надежду.",
            0
        ),
        Question(
            "Что обозначает белый круг на государственном флаге РС(Я)?",
            R.drawable.flag_sakha,
            "Белое солнце занимает большое место в якутской мифологии, Саха считают себя «детьми белого солнца»",
            0
        ),
        Question(
            "Что изображено на гербе Республики Саха (Якутия)?",
            0,
            "Представляет собой круг, в центре которого расположено изображение древнего всадника со знаменем с наскальных рисунков реки Лены на фоне солнечного диска-щита, помещенного в обрамление с традиционным национальным орнаментом в виде семи ромбических кристаллообразных фигур и надписями.",
            0
        ),
        Question(
            "Назовите авторов гимна Республики Саха (Якутия).",
            0,
            "На языке саха Саввой Тарасовым и Михаилом Тимофеевым. Гимн переведён на русский язык Владимиром Фёдоровым.",
            0
        ),
        Question(
            "Уникальное медицинское учреждение в Якутии, в котором могут делать сложнейшие операции по трансплантации органов, проводятся глубинные исследования здоровья, в том числе и генные, – это…",
            0,
            "Медцентр",
            0
        ),
        Question(
            "Назовите древнейшее эпическое искусство якутов, которое в 2009 году было включено в список «нематериального наследия человечества» под эгидой ЮНЕСКО?",
            0,
            "Олонхо́ (якут. Олоҥхо)",
            0
        ),
        Question(
            "В каком году Национальный парк «Ленские столбы» вошел в число объектов всемирного наследия ЮНЕСКО?",
            0,
            "в 2012 г.",
            0
        ),
        Question(
            "Как называлось первое учебное заведение высшего образования в Якутии?",
            0,
            "Якутский педагогический институт",
            0
        )
    )

    // Задания
    private val challengeList = listOf(
        "Передай право хода следующему игроку",
        "Переходи по речке на другой квадратик",
        "Таймаут. Пропускаешь ход"
    )

    private val tableChallengeList = listOf(
        "Выразительно прочитай стихотворение",
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