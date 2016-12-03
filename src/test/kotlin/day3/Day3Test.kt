package day3

import advent2016.day2.ArrayIndex
import advent2016.day2.Day2
import advent2016.day3.Day3
import io.kotlintest.specs.FlatSpec

class Day3Test : FlatSpec() {

    init {
        "The first part" should "work for the given cases"  {
            Day3("5 10 25").firstStar() shouldBe "0"
        }

        "The second part" should "work for the given case" {
            Day3().secondStar() shouldBe ""
        }
    }
}
