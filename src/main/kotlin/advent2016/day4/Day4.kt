package advent2016.day4

import advent2016.Day
import kotlin.comparisons.compareByDescending
import kotlin.comparisons.thenBy

class Day4(input: String? = null) : Day(input) {

    val inputs: List<Tuple> = generateInput()
    val secondStarInputs: List<Pair<String, Int>> = generateSecondStarInput()

    private fun  generateInput(): List<Tuple> {
        return super.inputString.split(Regex("\\n"))
                .map { string ->
                    val indexOfLastDash = string.lastIndexOf('-')
                    val indexOfBrace = string.indexOf('[')
                    val pair = string.substring(0, indexOfLastDash).replace("-", "")
                            .groupBy { it }.map { Pair(it.key, it.value.size) }.toMap()

                    Tuple(pair, string.substring(indexOfLastDash+1, indexOfBrace).toInt(), string.substring(indexOfBrace+1, string.length-1))
                }
    }

    private fun  generateSecondStarInput(): List<Pair<String, Int>> {
        return super.inputString.split(Regex("\\n"))
                .map {
                    val indexOfLastDash = it.lastIndexOf('-')
                    val indexOfBrace = it.indexOf('[')
                    Pair(it.substring(0, indexOfLastDash), it.substring(indexOfLastDash+1, indexOfBrace).toInt())
                }
    }


    private fun isValid(tuple: Tuple) : Boolean {
        val sorted = tuple.map.toList().sortedWith(compareByDescending<Pair<Char, Int>> { it.second }.thenBy { it.first } )
        return sorted.subList(0, tuple.checkSum.length).map { it.first.toString() }.joinToString("") == tuple.checkSum
    }

    override fun firstStar(): String {
        return inputs.filter { it -> isValid(it)}.sumBy { it.id }.toString()
    }

    override fun secondStar(): String {
        val stringIdPairs = secondStarInputs.map {
            pair -> Pair(cipher(pair.first, pair.second), pair.second) }
        return stringIdPairs.find { it.first.startsWith("northpole") }?.second.toString()
    }

    fun cipher(string: String, shift: Int) : String = string.map { cipher(it, shift) }.joinToString("").trim()

    fun cipher(char: Char, shift: Int) : Char {
        if (char == '-' || !Character.isAlphabetic(char.toInt())) return ' '
        val realShift = shift % 26
        return if (char + realShift > 'z') char - (26-realShift) else char + realShift
    }
}
data class Tuple(val map: Map<Char, Int>, val id: Int, val checkSum: String)
