package com.andystubbs.adventofcode2023.day5

import com.andystubbs.adventofcode2023.util.readInputIncludeBlank

fun main(args: Array<String>) {

    val input = readInputIncludeBlank("/day5/input.txt")

    val seeds = input.first { it.startsWith("seeds:") }.split("seeds: ").last().split(" ").map { it.toLong() }
    val maps = input.filter { !it.startsWith("seeds:") }.drop(1).joinToString("!").split("!!")

    val mappettes = HashMap<String, Mappette>()

    maps.forEach() {

        val items = it.split("!")
        val mapName = items.first().replace(" map:", "")
        val mappette = Mappette()

        val mappings = items.drop(1)

        mappings.forEach() {
            val values = it.split(" ").map { it.toLong() }
            mappette.addRange(Mapping(values[0], values[1], values[2]))
        }

        mappettes[mapName] = mappette
    }

    println(seeds.minOfOrNull {
        //Know this is ugly and you could do this much cleaner but..
        val seedToSoil = mappettes["seed-to-soil"]!!.map(it)
        val soilToFert = mappettes["soil-to-fertilizer"]!!.map(seedToSoil)
        val fertToWater = mappettes["fertilizer-to-water"]!!.map(soilToFert)
        val waterToLight = mappettes["water-to-light"]!!.map(fertToWater)
        val lightTemp = mappettes["light-to-temperature"]!!.map(waterToLight)
        val tempHumid = mappettes["temperature-to-humidity"]!!.map(lightTemp)
        val location = mappettes["humidity-to-location"]!!.map(tempHumid)
        location
    })

}

class Mapping(val destination: Long, val source: Long, val range: Long)

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
