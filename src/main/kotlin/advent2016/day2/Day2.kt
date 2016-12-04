package advent2016.day2

import advent2016.Day


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

    var actualPositionIndex = startPosition

    override fun firstStar(): String {
        return createMovement(keypad)
    }

    private fun createMovement(keyPad: List<List<Char?>>): String {
        return input.map {
            move(it, keyPad)
            keyPad[actualPositionIndex.row][actualPositionIndex.column]
        }.joinToString("")
    }

    private fun move(moves: CharSequence, keyPad: List<List<Char?>> = keypad) {
        moves.forEach { moveIfPossible(it, keyPad) }
    }

    private fun moveIfPossible(move: Char, keyPad: List<List<Char?>> = keypad) {
        when (move) {
            'L' -> if (canMoveLeft(keyPad)) actualPositionIndex.column--
            'R' -> if (canMoveRight(keyPad)) actualPositionIndex.column++
            'U' -> if (canMoveUp(keyPad)) actualPositionIndex.row--
            'D' -> if (canMoveDown(keyPad)) actualPositionIndex.row++
        }
    }

    private fun canMoveLeft(keyPad: List<List<Char?>>) = actualPositionIndex.column != 0 &&
            keyPad[actualPositionIndex.row][actualPositionIndex.column - 1] != null

    private fun canMoveRight(keyPad: List<List<Char?>>) = actualPositionIndex.column != keyPad[actualPositionIndex.row].size - 1 &&
            keyPad[actualPositionIndex.row][actualPositionIndex.column + 1] != null

    private fun canMoveUp(keyPad: List<List<Char?>>) = actualPositionIndex.row != 0 &&
            keyPad[actualPositionIndex.row - 1][actualPositionIndex.column] != null

    private fun canMoveDown(keyPad: List<List<Char?>>) = actualPositionIndex.row != keyPad.size - 1 &&
            keyPad[actualPositionIndex.row + 1][actualPositionIndex.column] != null


    override fun secondStar(): String {
        return createMovement(keypad2)
    }
}