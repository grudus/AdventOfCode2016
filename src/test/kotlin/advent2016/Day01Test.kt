package advent2016

import io.kotlintest.specs.FlatSpec

class Day01Test : FlatSpec() {

    init {
        "The first part" should "work for the given cases"  {
            Day01("R2, L3").firstStar() shouldBe "5"
            Day01("R2, R2, R2").firstStar() shouldBe "2"
            Day01("R5, L5, R5, R3").firstStar() shouldBe "12"
        }

        "The second part" should "work for the given case" {
            Day01("R8, R4, R4, R8").secondStar() shouldBe "4"
        }
    }


}
