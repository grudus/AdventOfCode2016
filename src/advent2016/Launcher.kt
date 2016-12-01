package advent2016

import advent2016.day1.Day1

fun main(args: Array<String>) {
    println("_____ It's time to start the Advent of Code! ____")

    val actualDay : Day = Day1()

    println("First star: ${actualDay.firstStar()}")
    actualDay.reset()
    println("Second star: ${actualDay.secondStar()}")
}