package com.andystubbs.adventofcode2023.day4

import com.andystubbs.adventofcode2023.util.readInput
import kotlin.math.pow

fun main(args: Array<String>) {

    val input = readInput("/day4/input.txt")

    print(input.sumOf {
        val numWins = numberOfWins(it)
        if (numWins > 0) 2.0.pow(numWins - 1) else 0.0
    }.toInt())
}

fun numberOfWins(line:String):Int {
    val card = line.split("|").first().split(":").last().split(" ").filter { it != "" }.map { it.toInt() }
    val numbers = line.split("|").last().split(" ").filter { it != "" }.map { it.toInt() }
    return card.intersect(numbers.toSet()).size
}