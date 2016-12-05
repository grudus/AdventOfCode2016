package advent2016

import java.security.MessageDigest

class Day5(input: String? = null) : Day(input) {

    val crypt: MessageDigest = MessageDigest.getInstance("MD5")

    override fun firstStar(): String {
        return findPassword(0, listOf(), { int, hash -> Pair(int, hash[5]) }).map { it.second }.joinToString("")
    }


    tailrec fun findPassword(index: Int, list: List<Pair<Int, Char>>, f: (Int, String) -> Pair<Int, Char>?): List<Pair<Int, Char>> {
        if (list.size == 8) return list

        val hashIndexPair = findValidHash(index)
        val indexCharPair = f.invoke(index, hashIndexPair.first)

        if (indexCharPair == null || list.map { it.first }.contains(indexCharPair.first))
            return findPassword(hashIndexPair.second + 1, list, f)

        return findPassword(hashIndexPair.second + 1, list + indexCharPair, f)
    }

    tailrec fun findValidHash(index: Int): Pair<String, Int> {
        val hash = findHash(inputString + index.toString())
        if (hash.startsWith("00000"))
            return Pair(hash, index)

        return findValidHash(index + 1)
    }

    fun findHash(actual: String): String {
        crypt.reset()
        return crypt.digest(actual.toByteArray())
                .map { String.format("%02x", it) }.joinToString("")
    }

    override fun secondStar(): String {
        return findPassword(0, listOf(), { int, hash ->
            if (hash[5].toDigit(16) > 7) null
            else Pair(hash[5].toDigit(), hash[6])
        }).sortedBy { it.first }.map { it.second }.joinToString("")
    }
}

fun Char.toDigit(radix: Int = 10) = Integer.parseInt(this.toString(), radix)