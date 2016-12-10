package advent2016

import java.util.*


class Day01(inputString: String? = null) : Day(inputString) {

    enum class Direction(val dx: Int, val dy: Int) {N(0, 1), E(1, 0), S(0, -1), W(-1, 0) }

    private val input = super.inputString.split(", ").map { Pair(findDirection(it[0]), it.find(Regex("\\d+")).toInt()) }
    var current = 0

    override fun firstStar(): String {
        val totalMove = input.groupBy { it.first }
                .map { Pair(it.key, it.value.sumBy { it.second }) }
                .map { Pair(it.first.dx * it.second, it.first.dy * it.second) }
                .reduce { p1, p2 -> Pair(p1.first + p2.first, p1.second + p2.second) }

        return (Math.abs(totalMove.first) + Math.abs(totalMove.second)).toString()
    }

    private fun findDirection(char: Char) : Direction {
        current = if (char == 'L') (4 + --current) % 4 else ++current % 4
        return Direction.values()[current]
    }

    override fun secondStar(): String {
        var actual = Pair(0, 0)
        val path = input.map { pair ->
            (0..pair.second-1).map {
                actual = Pair(actual.first + pair.first.dx, actual.second + pair.first.dy)
                actual
            }
        }.reduce { l1, l2 -> l1 + l2 }

        val repeatedPoint = path.find { Collections.frequency(path, it) > 1 } ?: Pair(0, 0)
        return (Math.abs(repeatedPoint.first) + Math.abs(repeatedPoint.second)).toString()
    }
}