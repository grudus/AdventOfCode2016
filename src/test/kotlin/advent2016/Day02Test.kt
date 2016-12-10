package advent2016

import io.kotlintest.specs.FlatSpec


class Day02Test : FlatSpec() {

    init {
        "The first part" should "work for the given cases"  {
            Day02("ULL\nRRDDD\nLURDL\nUUUUD", ArrayIndex(1, 1)).firstStar() shouldBe "1985"
        }

        "The second part" should "work for the given case" {
            Day02("ULL\nRRDDD\nLURDL\nUUUUD", ArrayIndex(2, 0)).secondStar() shouldBe "5DB3"
        }
    }


}