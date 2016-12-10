import advent2016.Day05
import io.kotlintest.specs.FlatSpec

class Day05Test : FlatSpec() {
	init{
        "The first part" should "work for the given cases"  {
			Day05("abc").firstStar() shouldBe "18f47a30"
		}

        "The second part" should "work for the given case" {
			Day05("abc").secondStar() shouldBe "05ace8e3"
        }
	}
}