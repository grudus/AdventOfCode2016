package advent2016


class Day3(input: String? = null) : Day(input) {

    val triangleSides: List<List<Int>> = super.inputString.split(Regex("\\n"))
            .map { it.trim().split(Regex("\\s+")) }
            .map { it.map(String::toInt) }


    override fun firstStar(): String {
        return countTriangles(triangleSides).toString()
    }

    override fun secondStar(): String {
        return countTriangles(generateVerticalTriangles()).toString()
    }

    private fun generateVerticalTriangles() : List<List<Int>> {
        return triangleSides.indices.map { index ->
            val row = index - (index % 3)
            (0..2).map { triangleSides[it + row][index % 3] }
        }
    }

    private fun countTriangles(matrix: List<List<Int>>) : Int {
        return matrix.filter {sides ->
            (0..2).filter { index -> sides.filterIndexed { i, side -> i != index }.sum() > sides[index]}.size == 3
        }.size
    }
}