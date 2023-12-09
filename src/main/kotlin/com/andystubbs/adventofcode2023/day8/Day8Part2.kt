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

    val startingPoints = nodeMap.keys.filter { it.endsWith("A") }
    val minsteps = startingPoints.map { getStepCount(nodeMap, instructions, it) }

    val result = lcm(minsteps)
    println(result)
}

fun getStepCount(
    nodeMap: HashMap<String, Triple<String, String, String>>,
    instructions: String,
    startPoint: String
): Long {
    var iCount = 0L
    var currentNode = nodeMap[startPoint]!!.first
    while (currentNode[2] != 'Z') {
        val direction = instructions[(iCount % instructions.length).toInt()]
        currentNode = if (direction == 'L') nodeMap[currentNode]!!.second else nodeMap[currentNode]!!.third
        iCount++
    }
    return iCount
}

fun gcd(num1: Long, num2: Long): Long {
    if (num1 == 0L) return num2
    return gcd(num2 % num1, num1)
}

fun lcm(numbers: List<Long>): Long {
    var lcm = numbers.first()

    numbers.drop(1).forEach {
        val gcd = gcd(lcm, it)
        lcm = (lcm * it) / gcd
    }
    return lcm
}
