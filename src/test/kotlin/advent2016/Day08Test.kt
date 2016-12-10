package advent2016

import io.kotlintest.specs.FlatSpec

class Day08Test : FlatSpec() {
	init{
        "The first part" should "work for the given cases"  {
			val day = Day08("rect 3x2\nrotate column x=1 by 1\nrotate row y=0 by 4\nrotate column x=1 by 1")
			day.firstStar() shouldBe "6"
		}

	}
}