package advent2016

import io.kotlintest.specs.FlatSpec


class Day4Test : FlatSpec() {
    init {
        "The first part" should "work for the given cases"  {
            Day4("""aaaaa-bbb-z-y-x-123[abxyz]
a-b-c-d-e-f-g-h-987[abcde]
not-a-real-room-404[oarel]
totally-real-room-200[decoy]""").firstStar() shouldBe "1514"
        }

        "The second part" should "work for the given case" {
            Day4().cipher("qzmt-zixmtkozy-ivhz-343", 343) shouldBe "very encrypted name"
        }
    }
}