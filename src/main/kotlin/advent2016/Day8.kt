package advent2016

import java.util.*

class Day8(input: String? = null) : Day(input) {

    val screen = Array(6, { Array(50, { false }) })

    fun makeScreen() = super.inputString.lines().forEach { line ->
        val numbers = Regex("\\d+").findAll(line).map { it.groupValues[0].toInt() }.toList()

        if (line.startsWith("rect")) createRect(numbers[0], numbers[1])
        else if (line.contains("col")) rotateColumn(numbers[0], numbers[1])
        else rotateRow(numbers[0], numbers[1])
    }


    fun createRect(width: Int, height: Int) {
        0.until(height).forEach { row ->
            0.until(width).forEach { col -> screen[row][col] = true }
        }
    }

    fun rotateColumn(startX: Int, shift: Int) {
        val column = screen.rotate()[startX].shift(shift)
        screen.indices.forEach { row -> screen[row][startX] = column[row] }
    }

    inline fun <reified T> Array<Array<T>>.rotate() = this[0].indices.map { col ->
        this.indices.map { row -> this[row][col] }.toTypedArray()
    }

    inline fun <reified T> Array<T>.shift(shift: Int): Array<T> {
        val list = this.toList()
        Collections.rotate(list, shift)
        return list.toTypedArray()
    }

    private fun rotateRow(startY: Int, shift: Int) {
        screen[startY] = screen[startY].shift(shift)
    }


    override fun firstStar(): String {
        makeScreen()
        return screen.reduce { row1, row2 -> row1 + row2 }.count { it }.toString()
    }

    override fun secondStar(): String {
        makeScreen()
        screen.map { it.map { if (it) "#" else " " } }.forEach { println(it.joinToString(" ")) }
        return "\n-1"
    }
}