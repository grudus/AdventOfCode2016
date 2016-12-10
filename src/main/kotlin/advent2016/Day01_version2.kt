package advent2016

import java.util.*


class Day01_version2(inputString: String? = null) : Day(inputString) {
    enum class Direction(val dx: Int, val dy: Int) {N(0, 1), E(1, 0), S(0, -1), W(-1, 0) }
    data class Point(val x: Int, val y: Int)
    data class Move(val where: Direction, val distance: Int)

    val input = findDirections(listOf(), 0, super.inputString.split(", "))

    tailrec fun findDirections(directions: List<Move>, currentDir: Int, remaining: List<String>): List<Move> {
        if (remaining.isEmpty()) return directions

        val newDirection = if (remaining[0][0] == 'L') (3 + currentDir) % 4 else (currentDir + 1) % 4
        val moves = remaining[0].find(Regex("\\d+")).toInt()
        val move = Move(Direction.values()[newDirection], moves)

        return findDirections(directions + move, newDirection, remaining.drop(1))
    }

    override fun firstStar(): String {
        val totalMove = input.groupBy(Move::where)
                .map { Pair(it.key, it.value.sumBy(Move::distance)) }
                .map { Pair(it.first.dx * it.second, it.first.dy * it.second) }
                .reduce { p1, p2 -> Pair(p1.first + p2.first, p1.second + p2.second) }

        return (Math.abs(totalMove.first) + Math.abs(totalMove.second)).toString()
    }

    override fun secondStar(): String {
        val paths = findPaths(Point(0, 0), input, listOf())
        val repeatedPoint = paths.find { Collections.frequency(paths, it) > 1 } ?: Point(0, 0)

        return (Math.abs(repeatedPoint.x) + Math.abs(repeatedPoint.y)).toString()
    }

    tailrec fun findPaths(actual: Point, remaining: List<Move>, path: List<Point>): List<Point> {
        if (remaining.isEmpty()) return path
        val points = (0..remaining[0].distance - 1).map { i ->
            Point(actual.x + remaining[0].where.dx * (i + 1), actual.y + remaining[0].where.dy * (i + 1))
        }

        return findPaths(points.last(), remaining.drop(1), path + points)
    }
}