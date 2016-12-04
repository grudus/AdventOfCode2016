package advent2016.day4

import advent2016.Day
import java.util.regex.Pattern
import kotlin.comparisons.compareByDescending
import kotlin.comparisons.thenBy

class Day4(input: String? = null) : Day(input) {

    private fun inputList(): List<Room> {
        return super.inputString.split(Regex("\\n")).map { string ->
            Room(string.find(Regex("[a-z-]+")), string.find(Regex("\\d+")).toInt(), string.find(Regex("(?<=\\[).+?(?=\\])")))
        }
    }

    override fun firstStar(): String {
        return inputList().filter { it -> isValid(it) }.sumBy(Room::id).toString()
    }

    private fun isValid(room: Room): Boolean {
        val sorted = room.charOccurrence().sortedWith(compareByDescending<Pair<Char, Int>> { it.second }.thenBy { it.first })
        return sorted.subList(0, room.checkSum.length).map { it.first.toString() }.joinToString("") == room.checkSum
    }

    override fun secondStar(): String {
        val stringIdPairs = inputList().map { room -> Pair(cipher(room.letters, room.id), room.id) }
        return stringIdPairs.find { it.first.startsWith("northpole") }?.second.toString()
    }

    fun cipher(string: String, shift: Int): String = string.map { cipher(it, shift) }.joinToString("").trim()

    fun cipher(char: Char, shift: Int): Char {
        if (char == '-' || !Character.isAlphabetic(char.toInt())) return ' '
        val realShift = shift % 26
        return if (char + realShift > 'z') char - (26 - realShift) else char + realShift
    }
}

data class Room(val letters: String, val id: Int, val checkSum: String) {
    fun charOccurrence() = letters.replace("-", "").groupBy { it }.map { Pair(it.key, it.value.size) }
}

fun String.find(regex: Regex): String {
    val m = Pattern.compile(regex.pattern).matcher(this)
    return if (m.find()) m.group() else ""
}
