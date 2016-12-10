package advent2016

import io.kotlintest.specs.FlatSpec

class Day09Test : FlatSpec() {
	init{
        "The first part" should "work for the given cases"  {
			Day09("ADVENT").firstStar() shouldBe "6"
			Day09("A(1x5)BC").firstStar() shouldBe "7"
			Day09("(3x3)XYZ").firstStar() shouldBe "9"
			Day09("A(2x2)BCD(2x2)EFG").firstStar() shouldBe "11"
			Day09("(6x1)(1x3)A").firstStar() shouldBe "6"
			Day09("X(8x2)(3x3)ABCY").firstStar() shouldBe "18"
		}

        "The second part" should "work for the given case" {
			Day09("(3x3)XYZ").secondStar() shouldBe "9"
			Day09("X(8x2)(3x3)ABCY").secondStar() shouldBe "20"
			Day09("(27x12)(20x12)(13x14)(7x10)(1x12)A").secondStar() shouldBe "241920"
			Day09("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN").secondStar() shouldBe "445"
        }
	}
}