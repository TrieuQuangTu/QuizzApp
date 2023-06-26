package com.example.quizzapp

object Constants {
    fun getQuestions() :ArrayList<Question>{

        val questionList = ArrayList<Question>()

        val que1 = Question(1,
            "What country flag is this?",
            R.drawable.australia,"Australia",
        "Argentina",
            "Armenia",
            "USA",1)

        val que2 = Question(2,
            "What country flag is this", R.drawable.mexico,
            "Australia",
            "Argentina",
            "Mexico",
            "USA",
            3)


        val que3 = Question(3,
            "What country flag is this", R.drawable.france,
            "Mexico",
            "France",
            "Africa",
            "USA",
            2)


        val que4 = Question(4,
            "What country flag is this", R.drawable.turkey,
            "Kazakistan",
            "Ukraine",
            "Turkey",
            "USA",
            3)


        val que5 = Question(5,
            "What country flag is this", R.drawable.us,
            "USA",
            "Argentina",
            "Armenia",
            "South America",
            1)


        val que6 = Question(6,
            "What country flag is this", R.drawable.uk,
            "Australia",
            "Argentina",
            "UK",
            "USA",
            3)


        val que7 = Question(7,
            "What country flag is this", R.drawable.european,
            "Scotland",
            "European Union",
            "Armenia",
            "USA",
            2)


        val que8 = Question(8,
            "What country flag is this", R.drawable.germany,
            "Netherlands",
            "Spain",
            "Belgium",
            "Germany",
            4)


        val que9 = Question(9,
            "What country flag is this", R.drawable.canada,
            "Denmark",
            "Argentina",
            "Canada",
            "USA",
            3)


        val que10 = Question(10,
            "What country flag is this", R.drawable.india,
            "India",
            "Iran",
            "Ireland",
            "USA",
            1)


        questionList.add(que1)
        questionList.add(que2)
        questionList.add(que3)
        questionList.add(que4)
        questionList.add(que5)
        questionList.add(que6)
        questionList.add(que7)
        questionList.add(que8)
        questionList.add(que9)
        questionList.add(que10)



        return questionList
    }
}