package advent2016.day1

import advent2016.Day
import java.io.File


class Day1 : Day() {

    val actualPosition = Point(0, 0)

    enum class Direction(val counter: Int) {

        N(0), E(1), S(2), W(3);

        companion object {
            fun from(findValue: Int): Direction = Direction.values().first { it.counter == findValue }
        }

    }

    private var actualDirection = Direction.N
    private val input: List<String>

    init {
        input = super.inputString.split(", ")
    }


    override fun firstStar(): String {
        input.map { Pair(it[0], it.substring(1).toInt()) }.forEach {
            handleChangedDirection(it)
        }

        return calculateDistance().toString()
    }

    private fun handleChangedDirection(it: Pair<Char, Int>) {
        when (it.first) {
            'R' -> {
                actualDirection = if (actualDirection.counter + 1 > 3) Direction.from(0) else Direction.from(actualDirection.counter + 1)
                go(it.second)
            }
            'L' -> {
                actualDirection = if (actualDirection.counter - 1 < 0) Direction.from(3) else Direction.from(actualDirection.counter - 1)
                go(it.second)
            }
        }
    }

    private fun go(distance: Int) {
        when (actualDirection) {
            Direction.N -> actualPosition.y += distance
            Direction.E -> actualPosition.x += distance
            Direction.S -> actualPosition.y -= distance
            Direction.W -> actualPosition.x -= distance
        }
    }

    private fun calculateDistance(p: Point = Point(actualPosition.x, actualPosition.y)) = Math.abs(p.x) + Math.abs(p.y)


    override fun secondStar(): String {
        val lines: MutableList<Line> = mutableListOf()

        input.map { Pair(it[0], it.substring(1).toInt()) }
                .forEach {
                    val previousPosition = actualPosition.copy()
                    handleChangedDirection(it)
                    val point = lines.find { it.intersect(Line(previousPosition, actualPosition)) }?.intersectPoint(Line(previousPosition, actualPosition))
                    if (point != null) {
                        return calculateDistance(point).toString()
                    }
                    lines.add(Line(previousPosition, actualPosition.copy()))
                }

        return "-1"
    }


}

data class Point(var x: Int, var y: Int)
class Line(val p1: Point, val p2: Point) {

    fun biggerX() = Math.max(p1.x, p2.x)
    fun smallerX() = Math.min(p1.x, p2.x)
    fun biggerY() = Math.max(p1.y, p2.y)
    fun smallerY() = Math.min(p1.y, p2.y)


    fun intersectPoint(anotherLine: Line): Point {

        val middleX = if (isHorizontal()) anotherLine.p1.x else p1.x
        val middleY = if (isHorizontal()) p1.y else anotherLine.p1.y

        return Point(middleX, middleY)
    }

    fun intersect(anotherLine: Line): Boolean {

        if (isParallel(anotherLine)) return false

        val horizontalOne = if (isHorizontal()) this else anotherLine
        val verticalOne = if (horizontalOne == this) anotherLine else this

        return horizontalOne.biggerX() > verticalOne.p1.x
                && horizontalOne.smallerX() < verticalOne.p1.x
                && verticalOne.biggerY() > horizontalOne.p1.y
                && verticalOne.smallerY() < horizontalOne.p1.y

    }

    fun isParallel(anotherLine: Line) = (isHorizontal() && anotherLine.isHorizontal())
            || (!isHorizontal() && !anotherLine.isHorizontal())

    fun isHorizontal() = p1.y - p2.y == 0

    override fun toString() = "(${p1.x},${p1.y}) -> (${p2.x}, ${p2.y})"
}