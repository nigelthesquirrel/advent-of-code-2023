package com.andystubbs.adventofcode2023.day5

import com.andystubbs.adventofcode2023.util.readInputIncludeBlank

fun main(args: Array<String>) {

    val input = readInputIncludeBlank("/day5/input.txt")

    val seeds = input.first { it.startsWith("seeds:") }.split("seeds: ").last().split(" ").map { it.toLong() }
    val maps = input.filter { !it.startsWith("seeds:") }.drop(1).joinToString("!").split("!!")

    val mappettes = HashMap<String, Mappette>()
    val mapNames = mutableListOf<String>()

    maps.forEach() {

        val items = it.split("!")
        val mapName = items.first().replace(" map:", "")
        mapNames.add(mapName)
        val mappette = Mappette()

        val mappings = items.drop(1)

        mappings.forEach() {
            val values = it.split(" ").map { it.toLong() }
            mappette.addRange(Mapping(values[0], values[1], values[2]))
        }

        mappettes[mapName] = mappette
    }

    println(seeds.minOfOrNull {

        var x:Long = it
        mapNames.forEach() {
            x = mappettes[it]!!.map(x)
        }
        x
    })

}

//If this wasn't a toy I would refactor this into  simply a list of LongRange

class Mapping(val destination: Long, val source: Long, val range: Long) {
    override fun toString():String {
        return "source=$source -> destination=$destination range=$range"
    }
}

class Mappette() {

    val mappings = mutableListOf<Mapping>()

    fun addRange(mapping: Mapping) {
        mappings.add(mapping)
    }

    fun map(value: Long): Long {
        mappings.forEach() {
            if (value >= it.source && value <= (it.source + it.range - 1)) return it.destination + (value - it.source)
        }
        return value
    }

    override fun toString(): String {
        return mappings.toString()
    }
}
