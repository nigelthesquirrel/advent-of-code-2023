package com.andystubbs.adventofcode2023.day1

import com.andystubbs.adventofcode2023.util.readInput

val wordsToValue = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9,
    "1" to 1,
    "2" to 2,
    "3" to 3,
    "4" to 4,
    "5" to 5,
    "6" to 6,
    "7" to 7,
    "8" to 8,
    "9" to 9
)

fun main(args: Array<String>) {

    val input = readInput("/day1/part2/input.txt")
    println(input.sumOf { "${firstValue(it)}${lastValue(it)}".toInt() })
}

fun firstValue(item: String) =
    wordsToValue[firstMatchingValues(item).minByOrNull { it.second }!!.first]

fun firstMatchingValues(item: String) =
    wordsToValue.keys.filter { item.contains(it) }.map { Pair(it, item.indexOf(it)) }

fun lastValue(item: String) =
    wordsToValue[lastMatchingValues(item).maxByOrNull { it.second }!!.first]

fun lastMatchingValues(item: String) =
    wordsToValue.keys.filter { item.contains(it) }.map { Pair(it, item.lastIndexOf(it)) }
