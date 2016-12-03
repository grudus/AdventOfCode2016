package day3

import advent2016.day3.Day3
import io.kotlintest.specs.FlatSpec

class Day3Test : FlatSpec() {

    init {
        "The first part" should "work for the given cases"  {
            Day3("5 10 25").firstStar() shouldBe "0"
        }

        "The second part" should "work for the given case" {
            Day3(
                    """101 301 501
102 302 502
103 303 503
201 401 601
202 402 602
203 403 603 """).secondStar() shouldBe "6"
        }
    }
}
