package day2

import advent2016.day1.Day1
import advent2016.day2.ArrayIndex
import advent2016.day2.Day2
import io.kotlintest.specs.FlatSpec


class Day2Test : FlatSpec() {

    init {
        "The first part" should "work for the given cases"  {
            Day2("ULL\nRRDDD\nLURDL\nUUUUD", ArrayIndex(1, 1)).firstStar() shouldBe "1985"
        }

        "The second part" should "work for the given case" {
            Day2("ULL\nRRDDD\nLURDL\nUUUUD", ArrayIndex(2, 0)).secondStar() shouldBe "5DB3"
        }
    }


}