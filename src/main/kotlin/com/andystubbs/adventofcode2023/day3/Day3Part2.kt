package com.andystubbs.adventofcode2023.day3

import com.andystubbs.adventofcode2023.util.readInput

val touchedGears = HashMap<Pair<Int,Int>,MutableList<Int>>()

fun main(args: Array<String>) {

    val input = readInput("/day3/input.txt")

    val grid = borderIt(input)

    var row = 0
    grid.forEach { updateTouchedGears(it, row++, grid) }
    println(touchedGears)
    print(touchedGears.values.sumOf { if (it.size == 2) it.first() * it.last() else 0 })

}

fun updateTouchedGears(line: String, y: Int, grid: List<String>) {

    var currentNumber = ""
    var currentNumberTouchedGears = mutableSetOf<Pair<Int,Int>>()

    for(x in 0 until line.length-1) {
        if(line[x].isDigit()) {
            currentNumber += line[x]
            currentNumberTouchedGears.addAll(gearsTouched(grid, y, x))
        }
        if(!line[x+1].isDigit() && currentNumber.isNotEmpty()) {

          currentNumberTouchedGears.forEach() {
              touchedGears.getOrPut(it) { mutableListOf<Int>() }.add(currentNumber.toInt())
          }
          currentNumber = ""
          currentNumberTouchedGears = mutableSetOf()
        }
    }

}

fun gearsTouched(grid: List<String>, row: Int, col: Int):List<Pair<Int,Int>> {

    val touchedGears = mutableListOf<Pair<Int,Int>>()

    for(x in -1..1) {
        for(y in -1..1) {
            if (grid[row+y][col+x] == '*') {
                touchedGears.add(Pair(row+y,col+x))
            }
        }
    }
    return touchedGears
}

