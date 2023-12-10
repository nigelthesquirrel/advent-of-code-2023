package com.andystubbs.adventofcode2023.day10

import com.andystubbs.adventofcode2023.util.readInput

enum class Move {
    UP, DOWN, LEFT, RIGHT
}

fun main(args: Array<String>) {
    val input = readInput("/day10/input.txt")

    val grid = borderIt(input)
    val start = findStart(grid)

    //printPretty(grid, start)

    var previous = start
    var current = start
    var next: Pair<Int, Int>
    var count = 0

    while(true) {
        next = nextMove(grid, current, previous)
        previous = current
        current = next
        //printPretty(grid, current)
        count++
        if(current == start) break
    }

    println("$count steps performed!")
    if(count%2 ==0 ) return println("Answer is ${count /2}") else println("Odd number!")
}

fun nextMove(grid: List<String>, from: Pair<Int, Int>, previous: Pair<Int, Int>): Pair<Int, Int> {

    for(m in Move.values()) {
        if(isValidMove(grid, from,previous, m)) {
            //println("$m is a valid move")
            return calculateMove(from, m)
        }
    }
    error("Can't find a valid move")
}

fun calculateMove(from: Pair<Int, Int>, move: Move): Pair<Int, Int> {
    return when (move) {
        Move.UP -> Pair(from.first - 1, from.second)
        Move.DOWN -> Pair(from.first + 1, from.second)
        Move.LEFT -> Pair(from.first, from.second - 1)
        Move.RIGHT -> Pair(from.first, from.second + 1)
    }
}

fun isValidMove(grid: List<String>, current: Pair<Int, Int>, previous: Pair<Int, Int>, move: Move): Boolean {

    val newLocation = calculateMove(current, move)
    val currentPipe = grid[current.first][current.second]
    val newPipe = grid[newLocation.first][newLocation.second]
    val isAllowed =
        getValidMoves()[move]!!.first.contains(currentPipe) && getValidMoves()[move]!!.second.contains(newPipe)
    val isPrevious = newLocation == previous
    return isAllowed && !isPrevious
}

fun getValidMoves() =
     mapOf(
        Move.UP to Pair(listOf('|', 'J', 'L', 'S'), listOf('|', '7', 'F', 'S')),
        Move.DOWN to Pair(listOf('|', '7', 'F', 'S'), listOf('|', 'J', 'L', 'S')),
        Move.RIGHT to Pair(listOf('-', 'L', 'F', 'S'), listOf('-', '7', 'J', 'S')),
        Move.LEFT to Pair(listOf('-', '7', 'J', 'S'), listOf('-', 'L', 'F', 'S')),
    )


fun findStart(grid: List<String>): Pair<Int, Int> {
    val y = grid.indexOfFirst { it.contains("S") }
    val x = grid.first { it.contains("S") }.indexOfFirst { it == 'S' }
    return Pair(y, x)
}

fun printPretty(input: List<String>, current: Pair<Int, Int>) {
    val replacements = mapOf('J' to '╝', '-' to '═', '7' to '╗', '|' to '║', 'F' to '╔', 'L' to '╚', '.' to '.', 'S' to 'S')

    for (y in input.indices) {
        for (x in 0 until input.first().length) {
            if (current.first == y && current.second == x) {
                print("X")
            } else {
                print(replacements[input[y][x]])
            }
        }
        println()
    }
}

/**
 * Given a list of strings representing a grid, draw a border of .s around it
 */
fun borderIt(x: List<String>): List<String> {
    val edgeRow = listOf(".".repeat(x.first().length + 2))
    return edgeRow + x.map { it -> ".${it}." } + edgeRow
}