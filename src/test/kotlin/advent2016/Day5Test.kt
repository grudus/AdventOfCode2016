import advent2016.Day5
import io.kotlintest.specs.FlatSpec

class Day5Test : FlatSpec() {
	init{
        "The first part" should "work for the given cases"  {
			Day5("abc").firstStar() shouldBe "18f47a30"
		}

        "The second part" should "work for the given case" {
			Day5("abc").secondStar() shouldBe "05ace8e3"
        }
	}
}