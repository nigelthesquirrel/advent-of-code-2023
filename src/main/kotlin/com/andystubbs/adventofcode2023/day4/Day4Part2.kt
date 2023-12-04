package com.andystubbs.adventofcode2023.day4

import com.andystubbs.adventofcode2023.util.readInput

fun main(args: Array<String>) {

    val input = readInput("/day4/input.txt")
    val indexScore = HashMap<Int, Int>()

    var count = 1
    input.forEach() { indexScore[count++] = numberOfWins(it) }

    val cardCount = HashMap<Int, Int>()
    for(i in 1..input.size) { cardCount[i] = 1 }

    for( i in 1..input.size) {
        for(p in 1..cardCount[i]!!) {
            for (n in 1..indexScore[i]!!) { cardCount[i + n] = cardCount[i + n]!! + 1 }
        }
    }

    println(cardCount.values.sum())
}

