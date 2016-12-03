package advent2016

import advent2016.day2.ArrayIndex
import advent2016.day2.Day2
import advent2016.day3.Day3

fun main(args: Array<String>) {
    println("_____ It's time to start the Advent of Code! ____")

    val actualDay = Day3()

    println("First star: ${actualDay.firstStar()}")

//    actualDay.reset {
//        actualDay.actualPositionIndex = ArrayIndex(2, 0)
//    }
//
//    println("Second star: ${actualDay.secondStar()}")
}
