package advent2016

class Day09(input: String? = null) : Day(input) {


    override fun firstStar(): String {
        return countLetters(initialWeights1, { bracket, how -> bracket + how - 1 }).toString()
    }

    override fun secondStar(): String {
        return countLetters(initialWeights2, { bracket, how -> bracket }).toString()
    }

    val initialWeights1 = super.inputString.map { 1L }.toTypedArray()
    val initialWeights2 = super.inputString.map { if (it.toString().matches(Regex("[A-Z]"))) 1L else 0L }.toTypedArray()

    fun countLetters(weights: Array<Long>, skip: (Int, Int) -> Int) = findWeights(0, inputString, weights, skip).sum()


    tailrec fun findWeights(index: Int, chars: String, weights: Array<Long>, skipFunction: (Int, Int) -> Int): Array<Long> {
        if (chars.isEmpty()) return weights

        if (chars[0].isLetter()) {
            val len = chars.find(Regex("\\w+")).length
            return findWeights(index + len, chars.drop(len), weights, skipFunction)
        }

        val numbers = chars.find(Regex("(\\d+)x(\\d+)")).split("x")
        val howManyChars = numbers[0].toInt()
        val howManyTimes = numbers[1].toInt()
        val bracketsLen = 3 + howManyChars.toString().length + howManyTimes.toString().length
        val startIndex = bracketsLen + index

        for (i in index..startIndex - 1)
            weights[i] = 0

        for (i in (startIndex)..(startIndex + howManyChars - 1)) {
            weights[i] *= howManyTimes.toLong()
        }
        val howManySkip = skipFunction.invoke(bracketsLen, howManyChars)

        return findWeights(howManySkip + index, chars.drop(howManySkip), weights, skipFunction)
    }

}