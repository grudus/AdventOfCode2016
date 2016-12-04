package advent2016.day4

import advent2016.Day
import kotlin.comparisons.compareByDescending
import kotlin.comparisons.thenBy

class Day4(input: String? = null) : Day(input) {

    val inputs: List<Tuple> = generateInput()

    private fun  generateInput(): List<Tuple> {
        return super.inputString.split(Regex("\\n"))
                .map {
                    val indexOfLastDash = it.lastIndexOf('-')
                    val indexOfBrace = it.indexOf('[')
                    val pair = it.substring(0, indexOfLastDash).replace("-", "")
                            .groupBy { it }.map { Pair(it.key, it.value.size) }.toMap()

                    Tuple(pair, it.substring(indexOfLastDash+1, indexOfBrace).toInt(), it.substring(indexOfBrace+1, it.length-1))
                }

    }

    private fun isValid(tuple: Tuple) : Boolean {
        val sorted = tuple.map.toList().sortedWith(compareByDescending<Pair<Char, Int>> { it.second }.thenBy { it.first } )
        return sorted.subList(0, tuple.checkSum.length).map { it.first.toString() }.reduce { s1, s2 -> s1+s2 } == tuple.checkSum
    }

    override fun firstStar(): String {
        return inputs.filter { it -> isValid(it)}.sumBy { it.id }.toString()

    }

    override fun secondStar(): String {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

data class Tuple(val map: Map<Char, Int>, val id: Int, val checkSum: String)
