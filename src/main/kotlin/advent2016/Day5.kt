package advent2016.day5

import advent2016.Day
import java.security.MessageDigest

class Day5(input: String? = null) : Day(input) {

    val crypt: MessageDigest = MessageDigest.getInstance("MD5")

    override fun firstStar(): String {
        var index = 0L
        var password = ""

        do {
            val hash = findHash(inputString + index++)
            if (hash.startsWith("00000")) password += hash[5]

        } while (password.length != 8)

        return password
    }

    fun findHash(actual: String): String {
        crypt.reset()
        return crypt.digest(actual.toByteArray())
                .map { String.format("%02x", it) }.joinToString("")
    }

    override fun secondStar(): String {
        var index = 0L
        val password = arrayOfNulls<Char>(8)
        var passwordChars: Int = 0

        do {
            val hash = findHash(inputString + index++)
            if (hash.startsWith("00000")) {
                if (Character.isAlphabetic(hash[5].toInt())) continue

                val idx = hash[5].toString().toInt()
                if (idx > 7 || password[idx] != null) continue
                password[idx] = hash[6]
                passwordChars++
            }

        } while (passwordChars != 8)

        return password.joinToString("")
    }

}