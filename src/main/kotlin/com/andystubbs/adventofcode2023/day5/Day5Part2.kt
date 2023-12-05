package com.andystubbs.adventofcode2023.day5

import com.andystubbs.adventofcode2023.util.readInputIncludeBlank

fun main(args: Array<String>) {

    val input = readInputIncludeBlank("/day5/input.txt")

    val seeds = input.first { it.startsWith("seeds:") }.split("seeds: ").last().split(" ").map { it.toLong() }
    val seedChunks = seeds.chunked(2)
    val finalSeeds = mutableSetOf<LongRange>()

    seedChunks.forEach() {
        finalSeeds.add(LongRange(it.first(), it.first() + (it.last() - 1)))
    }

    val maps = input.filter { !it.startsWith("seeds:") }.drop(1).joinToString("!").split("!!")
    val mapNames = mutableListOf<String>()
    val mappettes = HashMap<String, ReverseMappette>()

    maps.forEach() {

        val items = it.split("!")
        val mapName = items.first().replace(" map:", "")
        mapNames.add(mapName)
        val mappette = ReverseMappette()
        val mappings = items.drop(1)

        mappings.forEach() {
            val values = it.split(" ").map { it.toLong() }
            mappette.addRange(Mapping(values[0], values[1], values[2]))
        }

        mappettes[mapName] = mappette
    }

    mapNames.reverse()

    for (i in 1 until Long.MAX_VALUE) {
        var x:Long = i
        mapNames.forEach() { x = mappettes[it]!!.reverseMap(x) }
        finalSeeds.forEach() { if (it.contains(x)) { return println("Found it! $i") } }
    }
}

class ReverseMappette() {

    private val mappings = mutableListOf<Mapping>()

    fun addRange(mapping: Mapping) {
        mappings.add(mapping)
    }

    fun reverseMap(value: Long): Long {
        mappings.forEach() {
            if (value >= it.destination && value <= (it.destination + it.range - 1)) {
                return it.source + (value - it.destination)
            }
        }
        return value
    }

    override fun toString(): String {
        return mappings.toString()
    }
}
