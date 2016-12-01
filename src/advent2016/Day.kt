package advent2016

import java.io.File


abstract class Day {

    protected val inputString : String

    init {
        val day = javaClass.simpleName.substring(3).toInt()
        inputString = File(this.javaClass.classLoader.getResource("advent2016/day$day/input").toURI()).readText()
    }

    abstract fun firstStar() : String
    abstract fun secondStar() : String
}