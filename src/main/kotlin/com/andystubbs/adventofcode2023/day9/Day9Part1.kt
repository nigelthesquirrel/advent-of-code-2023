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

    val allLastItems = mutableListOf<Int>()

    for (i in input.indices) {

        val reductions = calcs[i]!!.reversed()
        var acc = reductions[0].last()
        reductions.drop(1).forEach() {
            acc += it.last()
        }

        val topLine = input[i].split(" ").map { it.toInt() }
        allLastItems.add(topLine.last() + acc)
    }

    println(allLastItems.sum())
}

fun difference(on: List<Int>): List<Int> {
    var prev = Int.MAX_VALUE;
    val newList = mutableListOf<Int>()
    on.forEach() {
        if (prev != Int.MAX_VALUE) {
            newList.add(it - prev)
        }
        prev = it
    }
    return newList
}

fun allZeros(list: List<Int>): Boolean {
    list.forEach() {
        if (it != 0) {
            return false
        }
    }
    return true
}

