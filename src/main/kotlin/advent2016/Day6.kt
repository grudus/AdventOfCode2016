package advent2016

import java.util.*

class Day6(input: String? = null) : Day(input) {

    val lines = super.inputString.split(Regex("\\n"))

    fun findWord(sorting: (List<Pair<Char, Int>>) -> List<Pair<Char, Int>>) = lines[0].indices.map { col ->
        lines.indices.map { row -> lines[row][col] }
    }.map { charList -> charList.map { char -> Pair(char, Collections.frequency(charList, char)) } }
            .map { sorting.invoke(it)[0] }
            .map(Pair<Char, Int>::first)
            .joinToString("")

    override fun firstStar(): String {
        return findWord { column -> column.sortedByDescending { it.second } }
    }

    override fun secondStar(): String {
        return findWord { column -> column.sortedBy { it.second } }
    }
}