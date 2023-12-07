package com.andystubbs.adventofcode2023.day6

import com.andystubbs.adventofcode2023.util.readInputIncludeBlank

fun main(args: Array<String>) {

    val input = readInputIncludeBlank("/day6/input.txt")

    val times = input.first().split("Time:").last().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
    val records = input.last().split("Distance:").last().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
    val timesRecords = times.zip(records)

    println(timesRecords.map() { calculateWaysToWin(it.first, it.second ) }.reduce { acc, i -> i * acc })
}

fun calculateWaysToWin(racetime: Int, record: Int): Int {

    val waysToWin = mutableListOf<Int>()

    for (buttonHold in 1 until racetime) {

        val remainingRaceTime = racetime - buttonHold
        val distance = remainingRaceTime * buttonHold
        if (distance > record) {
            waysToWin.add(buttonHold)
        }
    }
    return waysToWin.size
}