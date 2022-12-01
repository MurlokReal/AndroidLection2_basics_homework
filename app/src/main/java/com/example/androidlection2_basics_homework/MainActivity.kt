package com.example.androidlection2_basics_homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    class FootballMatch(var Score1: Int, var Score2: Int) {
        fun outputScore() {
            Log.d("MyLog", "Team1: $Score1 Team2: $Score2")
        }
        fun getGap() : Int {
            return abs(Score1 - Score2)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLog", "onStart insert")

        val matchList = mutableListOf<FootballMatch>()
        var s1: Int
        var s2: Int

        //Сгенерируем матчи и соберем их в список
        for (i in 0..9) {
            s1 = (0..5).random()
            s2 = (0..5).random()
            val match = FootballMatch(s1, s2)
            matchList += match
            match.outputScore()
        }

        //Соберем матчи с одинаковым счетом в одну коллекицю
        var matchesWithZeroGap = emptyArray<FootballMatch>()
        matchList.forEach() {if(it.Score1 == it.Score2) matchesWithZeroGap += it}

        //Удалим из списка матчи с одинаковым счетом
        matchList.removeAll(matchesWithZeroGap.toSet())

        Log.d("MyLog", "Match list cleared")

        matchList.forEach() {it.outputScore()}


        //Найдем матчи с самым большим разрывом очков и добавим их в новое множество
        val matchesWithMaxGap = hashSetOf<FootballMatch>()
        var maxGapMatch = matchList[0]

        matchList.forEach()
        {
            if (it.getGap() >= maxGapMatch.getGap()) maxGapMatch = it
        }

        matchList.forEach() {if(maxGapMatch.getGap() == it.getGap()) matchesWithMaxGap.add(it)}

        //Выведем матчи с наибольшим разрывом очков
        Log.d("MyLog", "Matches with max gap")
        matchesWithMaxGap.forEach { it.outputScore() }
    }
}