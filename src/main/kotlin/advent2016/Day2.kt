package advent2016


class Day2(input: String? = null, startPosition: ArrayIndex) : Day(input) {

    val input: List<String> = super.inputString.split(Regex("\\n"))

    private val keypad = listOf(
            listOf('1', '2', '3'),
            listOf('4', '5', '6'),
            listOf('7', '8', '9'))

    private val keypad2 = listOf(
            listOf(null, null, '1', null, null),
            listOf(null, '2', '3', '4', null),
            listOf('5', '6', '7', '8', '9'),
            listOf(null, 'A', 'B', 'C', null),
            listOf(null, null, 'D', null, null))

    var position = startPosition

    override fun firstStar(): String {
        return createMovement(keypad)
    }

    private fun createMovement(keyPad: List<List<Char?>>): String {
        return input.map {
            move(it, keyPad)
            keyPad[position.row][position.column]
        }.joinToString("")
    }

    private fun move(moves: CharSequence, keyPad: List<List<Char?>> = keypad) {
        moves.forEach { moveIfPossible(it, keyPad) }
    }

    private fun moveIfPossible(move: Char, keyPad: List<List<Char?>> = keypad) {
        when (move) {
            'L' -> if (canMove(keyPad, position.column - 1, position.row)) position.column--
            'R' -> if (canMove(keyPad, position.column + 1, position.row)) position.column++
            'U' -> if (canMove(keyPad, position.column, position.row - 1)) position.row--
            'D' -> if (canMove(keyPad, position.column, position.row + 1)) position.row++
        }
    }

    private fun canMove(keyPad: List<List<Char?>>, x: Int, y: Int) = x >= 0 && y >= 0
            && x < keyPad[0].size && y < keyPad.size && keyPad[x][y] != null


    override fun secondStar(): String {
        return createMovement(keypad2)
    }
}
data class ArrayIndex(var row: Int, var column: Int)