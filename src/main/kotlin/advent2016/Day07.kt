package advent2016

class Day07(input: String? = null) : Day(input) {

    data class Word(val word: String, val inBrackets: Boolean)

    override fun firstStar(): String {
        return super.inputString.lines().filter { supportsTLS(it) }.count().toString()
    }

    fun supportsTLS(ip: String) = ip.split(Regex("(\\[|\\])"))
            .mapIndexed { index, string -> Word(string, index % 2 == 1) }
            .filter { isAbba(it.word) }
            .isNotEmptyAndAllMatches { !it.inBrackets }

    fun <T> List<T>.isNotEmptyAndAllMatches(predicate: (T) -> Boolean) = this.isNotEmpty() && this.all(predicate)


    fun isAbba(string: String) = (0..string.length - 4).filter { i ->
        val sub1 = string.substring(i, i + 2)
        val sub2 = string.substring(i + 2, i + 4)
        sub1 == sub2.reversed() && sub1 != sub2
    }.isNotEmpty()

    override fun secondStar(): String {
        return super.inputString.lines().filter { supportsSSL(it) }.count().toString()
    }

    fun supportsSSL(ip: String): Boolean {
        val abas = ip.split(Regex("(\\[|\\])")).map { getAbas(it) }
        val inBrackets = abas.filterIndexed { i, list -> i % 2 == 1 }.reduce { l1, l2 -> l1 + l2 }
        val outsideBrackets = abas.filterIndexed { i, list -> i % 2 == 0 }.reduce { l1, l2 -> l1 + l2 }

        return inBrackets.filter { outsideBrackets.contains(it.abaToBab()) }.isNotEmpty()
    }

    fun getAbas(string: String): List<String> = (0..string.length - 3).map { string.substring(it, it + 3) }
            .filter { isAba(it) }

    fun isAba(string: String) = string.length == 3 && string[0] == string[2] && string[0] != string[1]

    fun String.abaToBab() = "${this[1]}${this[0]}${this[1]}"
}