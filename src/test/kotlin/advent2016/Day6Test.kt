package advent2016

import io.kotlintest.specs.FlatSpec

class Day6Test : FlatSpec() {
	init{
        "The first part" should "work for the given cases"  {
			Day6("""eedadn
drvtee
eandsr
raavrd
atevrs
tsrnev
sdttsa
rasrtv
nssdts
ntnada
svetve
tesnvt
vntsnd
vrdear
dvrsen
enarar""").firstStar() shouldBe "easter"
		}

        "The second part" should "work for the given case" {
			Day6("""eedadn
drvtee
eandsr
raavrd
atevrs
tsrnev
sdttsa
rasrtv
nssdts
ntnada
svetve
tesnvt
vntsnd
vrdear
dvrsen
enarar""").secondStar() shouldBe "advent"
        }
	}
}