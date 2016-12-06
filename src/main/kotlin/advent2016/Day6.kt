package advent2016

import java.util.*

class Day6(input: String? = null) : Day(input) {

    val charsByColumn = super.inputString.split(Regex("\\n")).map {
        it.toCharArray().toList() }.rotate()

    fun <T> List<List<T>>.rotate() = this[0].indices.map { col ->
        this.indices.map { row -> this[row][col] }
    }

    override fun firstStar(): String {
        return findWord { column -> column.sortedByDescending { it.second } }
    }

    override fun secondStar(): String {
        return findWord { column -> column.sortedBy { it.second } }
    }

    fun findWord(sorting: (List<Pair<Char, Int>>) -> List<Pair<Char, Int>>) = charsByColumn
            .map { charList -> charList.map { char -> Pair(char, Collections.frequency(charList, char)) } }
            .map { sorting.invoke(it)[0] }
            .map(Pair<Char, Int>::first)
            .joinToString("")

}