package advent2016

import java.io.File


abstract class Day(input: String? = null) {

    protected val inputString : String

    init {
        val day = try {javaClass.simpleName.substring(3).toInt()} catch (e: NumberFormatException) {4}
        inputString = input ?: File("src/main/resources/day$day/input").readText()
    }

    abstract fun firstStar() : String
    abstract fun secondStar() : String
    protected open fun reset(f: (Unit) -> Unit = {}) {}
}