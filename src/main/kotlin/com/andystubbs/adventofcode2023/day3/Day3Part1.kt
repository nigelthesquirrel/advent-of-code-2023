package com.andystubbs.adventofcode2023.day3

import com.andystubbs.adventofcode2023.util.readInput

fun main(args: Array<String>) {

    val input = readInput("/day3/input.txt")

    val numbers =  mutableListOf<Int>()

    val grid = borderIt(input)
    grid.forEach { println(it) }

    var row = 0
    grid.forEach {
        numbers.addAll(extractNumbers(it, row++, grid))
    }
    println(numbers)
    println(numbers.sum())
}

fun extractNumbers(line: String, y: Int, grid: List<String>): List<Int> {

    val validNumbers = mutableListOf<Int>()
    var currentNumber = ""
    var currentNumberTouching = false

    for(x in 0 until line.length-1) {
        if(line[x].isDigit()) {
            currentNumber += line[x]
            currentNumberTouching = currentNumberTouching || isTouchingSymbol(grid, y,x)
        }
        if(!line[x+1].isDigit() && currentNumber.isNotEmpty()) {

            if(currentNumberTouching) {
                validNumbers.add(currentNumber.toInt())
            }
          currentNumber = ""
          currentNumberTouching = false
        }
    }

    return validNumbers
}

/**
 * Given a list of strings representing a grid, draw a border of .s around it
 */
fun borderIt(x:List<String>): List<String> {
    val edgeRow = listOf(".".repeat(x.first().length+2))
    return edgeRow + x.map { it -> ".${it}." } + edgeRow
}

fun isSymbol(x: Char) = (!x.isDigit() && (x != '.'))

fun isTouchingSymbol(grid: List<String>, row: Int, col: Int):Boolean {
    for(x in -1..1) {
        for(y in -1..1) {
            if (isSymbol(grid[row+y][col+x])) return true
        }
    }
    return false
}

