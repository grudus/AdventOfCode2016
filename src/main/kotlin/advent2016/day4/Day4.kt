package advent2016.day4

import advent2016.Day
import java.util.regex.Pattern
import kotlin.comparisons.compareByDescending
import kotlin.comparisons.thenBy

class Day4(input: String? = null) : Day(input) {

    private fun firstStarInput(): List<Tuple> {
        return super.inputString.split(Regex("\\n"))
                .map { string ->
                    val charOccurrenceMap = string.replace("-", "").find(Regex("[a-z]+"))
                            .groupBy { it }.map { Pair(it.key, it.value.size) }.toMap()

                    Tuple(charOccurrenceMap, string.find(Regex("\\d+")).toInt(), string.find(Regex("(?<=\\[).+?(?=\\])")))
                }
    }

    private fun secondStarInput(): List<Pair<String, Int>> {
        return super.inputString.split(Regex("\\n"))
                .map { Pair(it.find(Regex("[a-z-]+")), it.find(Regex("\\d+")).toInt()) }
    }


    private fun isValid(tuple: Tuple): Boolean {
        val sorted = tuple.charOccurrence.toList().sortedWith(compareByDescending<Pair<Char, Int>> { it.second }.thenBy { it.first })
        return sorted.subList(0, tuple.checkSum.length).map { it.first.toString() }.joinToString("") == tuple.checkSum
    }

    override fun firstStar(): String {
        return firstStarInput().filter { it -> isValid(it) }.sumBy { it.id }.toString()
    }

    override fun secondStar(): String {
        val stringIdPairs = secondStarInput().map {
            pair ->
            Pair(cipher(pair.first, pair.second), pair.second)
        }
        return stringIdPairs.find { it.first.startsWith("northpole") }?.second.toString()
    }

    fun cipher(string: String, shift: Int): String = string.map { cipher(it, shift) }.joinToString("").trim()

    fun cipher(char: Char, shift: Int): Char {
        if (char == '-' || !Character.isAlphabetic(char.toInt())) return ' '
        val realShift = shift % 26
        return if (char + realShift > 'z') char - (26 - realShift) else char + realShift
    }
}

data class Tuple(val charOccurrence: Map<Char, Int>, val id: Int, val checkSum: String)

fun String.find(regex: Regex): String {
    val m = Pattern.compile(regex.pattern).matcher(this)
    return if (m.find()) m.group() else ""
}
