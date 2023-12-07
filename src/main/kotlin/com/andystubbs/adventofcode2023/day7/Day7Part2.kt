package com.andystubbs.adventofcode2023.day7

import com.andystubbs.adventofcode2023.util.readInputIncludeBlank
import kotlin.math.max

val rankingj = "AKQT98765432J".reversed()

fun main(args: Array<String>) {

    val input = readInputIncludeBlank("/day7/input.txt")
    val hands = input.map { Pair(it.split(" ").first(), it.split(" ").last().toLong()) }

    val sortedHands = hands.sortedWith(object : Comparator <Pair<String, Long>> {
        override fun compare (p0: Pair<String, Long>, p1: Pair<String, Long>) : Int {

            if(getMaxRanking(p0) > getMaxRanking(p1)) return 1
            if(getMaxRanking(p0) < getMaxRanking(p1)) return -1

            for(i:Int in 0 until 5) {
                if(getCardRank2(p0.first[i]) > getCardRank2(p1.first[i])) return 1
                if(getCardRank2(p0.first[i]) < getCardRank2(p1.first[i])) return -1
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

fun getMaxRanking(hand: Pair<String,Long>): Int {

    var possibleCards = hand.first.toCharArray().filter { it != 'J' }.toSet()
    if(possibleCards.isEmpty()) possibleCards =  setOf('A')

    var maxRanking = 0
    for(c in possibleCards) {
        val nonJokerHand = hand.first.replace('J',c)
        maxRanking = max(maxRanking, getRanking2(Pair(nonJokerHand, 0)))
    }
    return maxRanking
}

fun getRanking2(hand: Pair<String, Long>):Int {

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

fun getCardRank2(card: Char) = rankingj.indexOf(card)


