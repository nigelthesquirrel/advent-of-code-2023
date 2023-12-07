package com.andystubbs.adventofcode2023.day6

import com.andystubbs.adventofcode2023.util.readInputIncludeBlank

fun main(args: Array<String>) {

    val input = readInputIncludeBlank("/day6/input.txt")

    val time = input.first().split("Time:").last().split(" ").filter { it.isNotEmpty() }.joinToString(" ").replace(" ","").toLong()
    val record = input.last().split("Distance:").last().split(" ").filter { it.isNotEmpty() }.joinToString(" ").replace(" ","").toLong()

    println(calculateWaysToWin(time, record))
}


fun calculateWaysToWin(racetime: Long, record: Long): Int {

    var waysToWin = 0

    for (buttonHold in 1 until racetime) {

        val remainingRaceTime = racetime - buttonHold
        val distance = remainingRaceTime * buttonHold
        if (distance > record)  waysToWin++
    }
    return waysToWin
}

