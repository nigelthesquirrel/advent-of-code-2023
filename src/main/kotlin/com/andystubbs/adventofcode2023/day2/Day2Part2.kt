package com.andystubbs.adventofcode2023.day2

import com.andystubbs.adventofcode2023.util.readInput

fun main(args: Array<String>) {

    val input = readInput("/day2/part1/input.txt")

    println(input.sumOf { it ->
        val minCount = HashMap<String, Int>()
        val reveals = it.split(":").last().split(";")

        reveals.forEach {
            it.split(", ").forEach {
                val count = it.trimStart().split(" ").first().toInt()
                val color = it.trimStart().split(" ").last()
                minCount[color] = (minCount[color] ?: 0).coerceAtLeast(count)
            }
        }
        (minCount["blue"] ?: 1) * (minCount["green"] ?: 1) * (minCount["red"] ?: 1)
    })
}