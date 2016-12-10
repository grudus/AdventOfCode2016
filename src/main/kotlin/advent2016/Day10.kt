package advent2016

class Day10(input: String? = null) : Day(input) {

    data class Bot(val id: Int, val microphones: List<Int>) {
        fun smaller() = microphones.sorted()[0]
        fun bigger() = microphones.sorted()[1]
        fun add(microphone: Int) = Bot(id, microphones + listOf(microphone))
    }

    fun initBotsWithValues() =
            super.inputString.split(Regex("\\n"))
                    .filter { it.startsWith("value") }
                    .map {
                        val numbers = Regex("\\d+").findAll(it).toList().map { it.groupValues }
                        Pair(numbers[0][0].toInt(), numbers[1][0].toInt())
                    }
                    .groupBy { it.second }
                    .map { Bot(it.key, it.value.map { it.first }) }
                    .sortedBy { it.id }

    fun generateOrders() = inputString.split(Regex("\\n")).filter { !it.startsWith("value") }


    override fun firstStar(): String {
        return find(0, generateOrders(), listOf(), initBotsWithValues()).toString()

    }

    override fun secondStar(): String {
        return find(0, generateOrders(), listOf(), initBotsWithValues()).toString()
    }


    tailrec fun find(oldIndex: Int, orders: List<String>, outputs: List<Pair<Int, Int>>, bots: List<Bot>): Int {
        if (orders.isEmpty()) return outputs.sortedBy { it.first }.take(3).map { it.second }.reduce { i1, i2 -> i1*i2 }

        if (bots.any{it.microphones.contains(17) && it.microphones.contains(61)})
            println(bots.find { it.smaller() == 17 && it.bigger() == 61 })

        val index = oldIndex % orders.size

        val words = orders[index].split(Regex("\\s"))
        val givingBot = bots.find { it.id == words[1].toInt() }

        if (givingBot == null || givingBot.microphones.size < 2)
            return find(index + 1, orders, outputs, bots)

        val lowOutput = words[5] == "output"
        val highOutput = words[10] == "output"

        val lowIndex = words[6].toInt()
        val highIndex = words[11].toInt()

        val outputToAdd =
                if (lowOutput && highOutput) listOf(Pair(lowIndex, givingBot.smaller()), Pair(highIndex, givingBot.bigger()))
                else if (lowOutput) listOf(Pair(lowIndex, givingBot.smaller()))
                else if (highOutput) listOf(Pair(highIndex, givingBot.bigger()))
                else listOf()

        val lowBot = if (lowOutput) null else bots.find { it.id == lowIndex } ?: Bot(lowIndex, listOf())
        val highBot = if (highOutput) null else bots.find { it.id == highIndex } ?: Bot(highIndex, listOf())

        val botsToAdd =
                if (lowBot != null && highBot != null) listOf(lowBot.add(givingBot.smaller()), highBot.add(givingBot.bigger()))
                else if (lowBot != null) listOf(lowBot.add(givingBot.smaller()))
                else if (highBot != null) listOf(highBot.add(givingBot.bigger()))
                else listOf()

        val filteredBots = bots.map { bot -> botsToAdd.find { it.id == bot.id } ?: bot}
                .filter { it.id != givingBot.id }

        return find(index, orders.without(index), outputs + outputToAdd, (filteredBots + botsToAdd))
    }
}

fun <T> List<T>.without(index: Int) = this.filterIndexed { i, t -> i != index }