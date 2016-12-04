package day4

import advent2016.day4.Day4
import io.kotlintest.specs.FlatSpec


class Day4Test : FlatSpec() {
    init {
        "The first part" should "work for the given cases"  {
            Day4("""aaaaa-bbb-z-y-x-123[abxyz]
a-b-c-d-e-f-g-h-987[abcde]
not-a-real-room-404[oarel]
totally-real-room-200[decoy]""").firstStar() shouldBe "1514"
        }

//        "The second part" should "work for the given case" {
//            Day3(
//                    """101 301 501
//102 302 502
//103 303 503
//201 401 601
//202 402 602
//203 403 603 """).secondStar() shouldBe "6"
//        }
    }
}