package com.andystubbs.adventofcode2023.day7

import com.andystubbs.adventofcode2023.util.readInputIncludeBlank

val ranking = "AKQJT98765432".reversed()

fun main(args: Array<String>) {

    val input = readInputIncludeBlank("/day7/input.txt")
    val hands = input.map { Pair(it.split(" ").first(), it.split(" ").last().toLong()) }

    val sortedHands = hands.sortedWith(object : Comparator <Pair<String, Long>> {
        override fun compare (p0: Pair<String, Long>, p1: Pair<String, Long>) : Int {

            if(getRanking(p0) > getRanking(p1)) return 1
            if(getRanking(p0) < getRanking(p1)) return -1

            for(i:Int in 0 until 5) {
                if(getCardRank(p0.first[i]) > getCardRank(p1.first[i])) return 1
                if(getCardRank(p0.first[i]) < getCardRank(p1.first[i])) return -1
            }

            return 0
        }
    })

    var total:Long = 0;
    for(i:Int in sortedHands.indices) {
        total += (sortedHands[i].second * (i + 1))
    }

    println(total)
}

fun getRanking(hand: Pair<String, Long>):Int {

    val cluster = hand.first.toList().groupingBy { it }.eachCount().values.max()

    if( cluster == 5) return 7
    if( cluster == 4) return 6

    if (
        hand.first.toList().groupingBy { it }.eachCount().values.contains(2) &&
        hand.first.toList().groupingBy { it }.eachCount().values.contains(3)
    ) return 5

    if( cluster == 3) return 4
    if( cluster == 1) return 1

    val numPairs = hand.first.toList().groupingBy { it }.eachCount().values.count { it == 2 }
    if( numPairs == 2) return 3
    if( numPairs == 1) return 2
    error("Something wrong!")
}

fun getCardRank(card: Char) = ranking.indexOf(card)


