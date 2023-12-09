package com.andystubbs.adventofcode2023.day9

import com.andystubbs.adventofcode2023.util.readInputIncludeBlank


fun main(args: Array<String>) {
    val input = readInputIncludeBlank("/day9/input.txt")

    val calcs = hashMapOf<Int, MutableList<List<Int>>>()

    var row = 0;
    input.forEach() {

        val reductions = mutableListOf<List<Int>>()
        val topLine = it.split(" ").map { it.toInt() }

        var currentList = topLine

        while (!allZeros(currentList)) {
            currentList = difference(currentList)
            reductions.add(currentList)
        }

        calcs[row++] = reductions
    }

    val allFirstItems = mutableListOf<Int>()

    for (i in input.indices) {

        val reductions = calcs[i]!!.reversed()
        var acc = reductions[0].first()
        reductions.drop(1).forEach() {
            acc = it.first() - acc
        }

        val topLine = input[i].split(" ").map { it.toInt() }
        allFirstItems.add(topLine.first() - acc)
    }

    println(allFirstItems.sum())
}




