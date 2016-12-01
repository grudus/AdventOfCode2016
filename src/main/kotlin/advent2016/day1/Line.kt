package advent2016.day1

class Line(val p1: Point, val p2: Point) {

    init {
        if (!isHorizontal() && !isVertical())
            throw IllegalArgumentException("Line must be vertical or horizontal")
    }

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
    fun isVertical() = p1.x - p2.x == 0

    override fun toString() = "(${p1.x},${p1.y}) -> (${p2.x}, ${p2.y})"
}
