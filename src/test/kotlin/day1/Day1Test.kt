package day1

import advent2016.day1.Day1
import io.kotlintest.specs.FlatSpec

class Day1Test : FlatSpec() {

    init {
        "The first part" should "work for the given cases"  {
            Day1("R2, L3").firstStar() shouldBe "5"
            Day1("R2, R2, R2").firstStar() shouldBe "2"
            Day1("R5, L5, R5, R3").firstStar() shouldBe "12"
        }

        "The second part" should "work for the given case" {
            Day1("R8, R4, R4, R8").secondStar() shouldBe "4"
        }
    }


}
