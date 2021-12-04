data class Cell(val number: Int, val marked: Boolean)

typealias Board = List<List<Cell>>

const val boardSize = 5

fun boardScore(board: Board, calledNumber: Int): Int {
    return board.flatten().filter { !it.marked }.sumOf { it.number } * calledNumber
}

fun hasWon(board: Board): Boolean {
    for (line in board) {
        if (line.all { it.marked }) {
            return true
        }
    }
    for (rowIndex in board.first().indices) {
        if (board.all { it[rowIndex].marked }) {
            return true
        }
    }
    return false
}

fun updateBoard(board: Board, number: Int): Board {
    return board.map { line ->
        line.map {
            if (it.number == number) {
                Cell(number, true)
            } else it
        }
    }
}

fun readBoards(input: List<String>): List<Board> {
    return input.drop(1).chunked(boardSize + 1) // +1 for empty line
        .map { boardLines ->
            boardLines.drop(1).map { boardLine ->
                boardLine.split(Regex("\\s+")).filter { it.isNotEmpty() }.map { Cell(it.toInt(), false) }
            }
        }
}

fun part1(input: List<String>): Int {
    val bingoNumbers = input.first().split(",").map { it.toInt() }
    var boards = readBoards(input)
    for (number in bingoNumbers) {
        boards = boards.map { updateBoard(it, number) }
        for (board in boards) {
            if (hasWon(board)) {
                return boardScore(board, number)
            }
        }
    }
    throw IllegalStateException("No boards won")
}

fun part2(input: List<String>): Int {
    val bingoNumbers = input.first().split(",").map { it.toInt() }
    var boards = readBoards(input)
    for (number in bingoNumbers) {
        val lastNotWonIndex = boards.withIndex().first { !hasWon(it.value) }.index
        boards = boards.map { updateBoard(it, number) }
        if (boards.all { hasWon(it) }) {
            return boardScore(boards[lastNotWonIndex], number)
        }
    }
    throw IllegalStateException("More than one board lost")
}

fun main() {
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}