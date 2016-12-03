package advent2016.day3

import advent2016.Day


class Day3(input: String? = null) : Day(input) {

    val triangleSides: List<List<Int>> = super.inputString.split(Regex("\\n"))
            .map { it.trim().split(Regex("\\s+")) }
            .map { it.map(String::toInt) }

    val verticalTriangleSides: List<List<Int>> = (0..triangleSides.size-1).map {index ->
        val row = index - (index % 3)
        (0..2).map { triangleSides[it+row][index%3] }
    }


    override fun firstStar(): String {
        return countTriangles(triangleSides).toString()
    }

    override fun secondStar(): String {
        return countTriangles(verticalTriangleSides).toString()
    }

    private fun countTriangles(matrix: List<List<Int>>) : Int {
        return matrix.filter {sides ->
            (0..2).filter { index -> sides.filterIndexed { i, side -> i != index }.sum() > sides[index]}.size == 3
        }.size
    }

    override fun reset(f: (Unit) -> Unit) {}
}