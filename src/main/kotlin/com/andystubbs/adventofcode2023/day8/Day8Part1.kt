package com.andystubbs.adventofcode2023.day8

import com.andystubbs.adventofcode2023.util.readInputIncludeBlank


fun main(args: Array<String>) {
    val input = readInputIncludeBlank("/day8/input.txt")
    val instructions = input.first()
    val descriptions = input.filter { it.contains("=") }

    val nodeMap = HashMap<String, Triple<String, String, String>>()

    descriptions.forEach() {
        val nodeName = it.split(" = ").first()
        val leftRight = it.split(" = ").last().replace(" ", "").replace("(", "").replace(")", "")
        val (left, right) = leftRight.split(",")
        val node = Triple(nodeName, left, right)

        nodeMap[nodeName] = node
    }

    var iCount = 0
    var currentNode = nodeMap["AAA"]!!.first
    while(currentNode != "ZZZ") {
        var direction = instructions[iCount % instructions.length]
        currentNode = if(direction == 'L') nodeMap[currentNode]!!.second else nodeMap[currentNode]!!.third
        iCount++
    }

    println(iCount)
}




