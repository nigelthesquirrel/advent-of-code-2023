package com.andystubbs.adventofcode2023.day10

import com.andystubbs.adventofcode2023.util.readInput

var inCount = 0
val marker = mutableListOf<Pair<Int,Int>>()

fun main(args: Array<String>) {

    val input = readInput("/day10/input.txt")

    val grid = borderIt(input)
    val start = findStart(grid)

    val path = mutableListOf<Pair<Int,Int>>()
    path.add(start)

    var previous = start
    var current = start
    var next: Pair<Int, Int>

    while(true) {
        next = nextMove(grid, current, previous)
        path.add(next)
        previous = current
        current = next
        if(current == start) break
    }


    var inOut = "O"

    for (y in 0 until grid.size - 1) {
        for (x in 0 until grid.first().length) {

            inOut = calculateInOut(inOut, grid, Pair(y, x), path)
            //printPretty(grid, Pair(y, x), inOut)
        }
    }
    printPretty(grid, Pair(0,0), " ")
    print(inCount)
}

fun calculateInOut(inOut: String, grid: List<String>, location: Pair<Int, Int>, path: List<Pair<Int,Int>>): String {

    if (listOf('7', '|', 'F', 'S').contains(grid[location.first][location.second]) &&
        listOf('J', '|', 'L').contains(grid[location.first + 1][location.second]) &&
        path.contains(location)
    ) {
        if (inOut == "I") return "O"
        if (inOut == "O") return "I"
    }

    if(inOut == "I" && !path.contains(location)) {
        inCount++
        marker.add(Pair(location.first,location.second))
    }

    return inOut
}

fun printPretty(input: List<String>, current: Pair<Int, Int>, insideOutside: String) {
    val replacements =
        mapOf('J' to '╝', '-' to '═', '7' to '╗', '|' to '║', 'F' to '╔', 'L' to '╚', '.' to '.', 'S' to 'S')

    for (y in input.indices) {
        for (x in 0 until input.first().length) {
            if (current.first == y && current.second == x) {
                print(insideOutside)
            } else {

                if(marker.contains(Pair(y,x))){
                    print("I")
                } else {
                    print(replacements[input[y][x]])
                }
            }
        }
        println()
    }
    println()
}

