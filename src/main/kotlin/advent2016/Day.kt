package advent2016

import java.io.File


abstract class Day(input: String? = null) {

    protected val inputString : String

    init {
        val day = javaClass.simpleName.substring(3).toInt()
        inputString = input ?: File("src/main/resources/day$day/input").readText()
    }

    abstract fun firstStar() : String
    abstract fun secondStar() : String
    abstract fun reset(f: (Unit) -> Unit = {})
}