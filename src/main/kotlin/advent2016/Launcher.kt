package advent2016

import advent2016.day3.Day3

fun main(args: Array<String>) {
    println("_____ It's time to start the Advent of Code! ____")

    val actualDay = Day3()

    println("First star: ${actualDay.firstStar()}")
    println("Second star: ${actualDay.secondStar()}")
}
