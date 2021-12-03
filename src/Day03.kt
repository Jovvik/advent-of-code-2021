fun main() {
    fun isMostCommon(input: List<String>, i : Int, c : Char) : Boolean {
        return input.map { it[i] }.filter { it == c }.size >= input.size.toDouble() / 2
    }

    fun part1(input: List<String>): Int {
        var gamma = 0
        var eps = 0
        val numLength = input.first().length
        for (i in 0 until numLength) {
            gamma *= 2
            eps *= 2
            if (isMostCommon(input, i, '1')) {
                gamma++
            } else {
                eps++
            }
        }
        return gamma * eps
    }

    fun getRating(input: List<String>, criterion: (List<String>, Int) -> List<String>): Int {
        var report = input
        for (i in 0 until input.first().length) {
            report = criterion(report, i)
            if (report.size == 1) {
                return report.first().toInt(2)
            }
        }
        throw IllegalStateException("No unique value")
    }


    fun oxygen(input: List<String>): Int = getRating(input) { report, i ->
        val mostCommon = if (isMostCommon(report, i, '1')) '1' else '0'
        report.filter { it[i] == mostCommon }
    }

    fun co2(input: List<String>): Int = getRating(input) { report, i ->
        val mostCommon = if (isMostCommon(report, i, '1')) '0' else '1'
        report.filter { it[i] == mostCommon }
    }

    fun part2(input: List<String>): Int {
        return oxygen(input) * co2(input)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}