package advent2016

import io.kotlintest.specs.FlatSpec

class Day9Test : FlatSpec() {
	init{
        "The first part" should "work for the given cases"  {
			Day9("ADVENT").firstStar() shouldBe "6"
			Day9("A(1x5)BC").firstStar() shouldBe "7"
			Day9("(3x3)XYZ").firstStar() shouldBe "9"
			Day9("A(2x2)BCD(2x2)EFG").firstStar() shouldBe "11"
			Day9("(6x1)(1x3)A").firstStar() shouldBe "6"
			Day9("X(8x2)(3x3)ABCY").firstStar() shouldBe "18"
		}

        "The second part" should "work for the given case" {
			Day9("(3x3)XYZ").secondStar() shouldBe "9"
			Day9("X(8x2)(3x3)ABCY").secondStar() shouldBe "20"
			Day9("(27x12)(20x12)(13x14)(7x10)(1x12)A").secondStar() shouldBe "241920"
			Day9("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN").secondStar() shouldBe "445"
        }
	}
}