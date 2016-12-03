package advent2016.day3

import advent2016.Day


class Day3(input: String? = null) : Day(input) {

    val triangleSides: List<List<Int>> = super.inputString.split(Regex("\\n"))
            .map { it.trim().split(Regex("\\s+")) }
            .map { it.map(String::toInt) }

    override fun firstStar(): String {
        val size = triangleSides.filter {sides ->
            (0..2).filter { index -> sides.filterIndexed { i, side -> i != index }.sum() > sides[index]}.size == 3
        }.size
        return size.toString()
    }

    override fun secondStar(): String {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun reset(f: (Unit) -> Unit) {}
}