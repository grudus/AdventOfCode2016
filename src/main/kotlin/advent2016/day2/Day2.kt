package advent2016.day2

import advent2016.Day


class Day2(input: String? = null, startPosition: ArrayIndex) : Day(input) {

    val input: List<String> = super.inputString.split(Regex("\\n"))
    val NULL = 'X'


    private val keypad = arrayOf(
            arrayOf('1', '2', '3'),
            arrayOf('4', '5', '6'),
            arrayOf('7', '8', '9'))

    private val keypad2 = arrayOf(
            arrayOf(NULL, NULL, '1', NULL, NULL),
            arrayOf(NULL, '2', '3', '4', NULL),
            arrayOf('5', '6', '7', '8', '9'),
            arrayOf(NULL, 'A', 'B', 'C', NULL),
            arrayOf(NULL, NULL, 'D', NULL, NULL))

    var actualPositionIndex = startPosition

    override fun firstStar(): String {
        var output = ""
        input.forEach {
            move(it)
            output += keypad[actualPositionIndex.row][actualPositionIndex.column]
        }
        return output
    }

    private fun move(moves: CharSequence, keyPad: Array<Array<Char>> = keypad) {
        moves.forEach {
            if (possibleMove(it, keyPad))
                makeMove(it)
        }

    }

    private fun possibleMove(move: Char, keyPad: Array<Array<Char>> = keypad): Boolean {
        when (move) {
            'L' -> if (isLeftEdge() || !canMoveLeft(keyPad)) return false
            'R' -> if (isRightEdge(keyPad) || !canMoveRight(keyPad)) return false
            'U' -> if (isTopEdge() || !canMoveUp(keyPad)) return false
            'D' -> if (isBottomEdge(keyPad) || !canMoveDown(keyPad)) return false
        }
        return true
    }

    private fun  isLeftEdge() = actualPositionIndex.column == 0
    private fun  canMoveLeft(keyPad: Array<Array<Char>>) = keyPad[actualPositionIndex.row][actualPositionIndex.column - 1] != NULL
    
    private fun  isRightEdge(keyPad: Array<Array<Char>>) = actualPositionIndex.column == keyPad[actualPositionIndex.row].size - 1
    private fun  canMoveRight(keyPad: Array<Array<Char>>) = keyPad[actualPositionIndex.row][actualPositionIndex.column + 1] != NULL
    
    private fun  isTopEdge() = actualPositionIndex.row == 0
    private fun canMoveUp(keyPad: Array<Array<Char>>) = keyPad[actualPositionIndex.row - 1][actualPositionIndex.column] != NULL
    
    private fun isBottomEdge(keyPad: Array<Array<Char>>) = actualPositionIndex.row == keyPad.size - 1
    private fun canMoveDown(keyPad: Array<Array<Char>>) = keyPad[actualPositionIndex.row + 1][actualPositionIndex.column] != NULL


    private fun makeMove(char: Char) {
        when (char) {
            'L' -> actualPositionIndex.column -= 1
            'R' -> actualPositionIndex.column += 1
            'U' -> actualPositionIndex.row -= 1
            'D' -> actualPositionIndex.row += 1

        }
    }

    override fun secondStar(): String {
        var output = ""
        input.forEach {
            move(it, keypad2)
            output += keypad2[actualPositionIndex.row][actualPositionIndex.column]
        }
        return output
    }
}