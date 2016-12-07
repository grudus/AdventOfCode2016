package advent2016

import io.kotlintest.specs.FlatSpec

class Day7Test : FlatSpec() {
	init{
        "The first part" should "work for the given cases"  {
			val day = Day7("")
			day.supportsTLS("abba[mnop]qrst") shouldBe true
			day.supportsTLS("abcd[bddb]xyyx") shouldBe false
			day.supportsTLS("aaaa[qwer]tyui") shouldBe false
			day.supportsTLS("ioxxoj[asdfgh]zxcvbn") shouldBe true
		}

        "The second part" should "work for the given case" {
			val day = Day7("")
			day.supportsSSL("aba[bab]xyz") shouldBe true
			day.supportsSSL("xyx[xyx]xyx") shouldBe false
			day.supportsSSL("aaa[kek]eke") shouldBe true
			day.supportsSSL("zazbz[bzb]cdb") shouldBe true
        }
	}
}