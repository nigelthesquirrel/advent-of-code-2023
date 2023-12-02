package com.andystubbs.adventofcode2023.day1

import com.andystubbs.adventofcode2023.util.readInput

fun main(args: Array<String>) {

    val input = readInput("/day1/part1/input.txt")
    println(input.sumOf { "${firstDigit(it)}${lastDigit(it)}".toInt() })
}

fun firstDigit(input: String) =
    input.first { it.isDigit() }

fun lastDigit(input: String) =
    input.last { it.isDigit() }