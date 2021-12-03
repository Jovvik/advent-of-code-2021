fun main() {
    fun part1(input: List<String>): Int {
        var depth = 0
        var horizontal = 0
        input.map { it.split(" ") }.forEach {
            val command = it[0]
            val change = it[1].toInt()
            when (command) {
                "forward" -> horizontal += change
                "down" -> depth += change
                "up" -> depth -= change
            }
        }
        return depth * horizontal
    }

    fun part2(input: List<String>): Int {
        var depth = 0
        var horizontal = 0
        var aim = 0
        input.map { it.split(" ") }.forEach {
            val command = it[0]
            val change = it[1].toInt()
            when (command) {
                "forward" -> {
                    horizontal += change
                    depth += aim * change
                }
                "down" -> aim += change
                "up" -> aim -= change
            }
        }
        return depth * horizontal
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}