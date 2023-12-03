package com.andystubbs.adventofcode2023.day2

import com.andystubbs.adventofcode2023.util.readInput


class Day2Part1 {
}

fun main(args: Array<String>) {

    val input = readInput("/day2/part1/input.txt")
    val totalCubes = mapOf("red" to 12, "green" to  13, "blue" to 14)
    val invalidGames = HashSet<Int>();

    input.forEach { it ->
        val maxCount = HashMap<String, Int>()
        val gameNumber = it.split(":").first().replace("Game ", "").toInt()
        val reveals = it.split(":").last().split(";")

        reveals.forEach {
            val cubes = it.split(", ")
            cubes.forEach {
                val count = it.trimStart().split(" ").first().toInt()
                val color = it.trimStart().split(" ").last()
                maxCount[color] = (maxCount[color] ?: 0).coerceAtLeast(count)
            }
        }
        maxCount.entries.forEach() { if (it.value > (totalCubes[it.key] ?: 0)) invalidGames.add(gameNumber) }
    }

    val validGames = HashSet<Int>()
    for(c in 1..input.size) {
        if(!invalidGames.contains(c)) validGames.add(c)
    }

    println(validGames.sum())
}