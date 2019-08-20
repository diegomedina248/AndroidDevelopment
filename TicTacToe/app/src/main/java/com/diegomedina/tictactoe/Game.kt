package com.diegomedina.tictactoe

class Game {
    var player = "X"
        private set

    private val board = Board()

    fun makeMove(row: Int, col: Int) {
        board.makeMove(player, row, col)
        player = if (player == "X") "O" else "X"
    }

    fun playerWon() = when {
        checkPlayerWon("X") -> "X"
        checkPlayerWon("O") -> "O"
        else -> null
    }

    fun draw() = playerWon() == null
            && !board.hasEmptyTile()

    private fun checkPlayerWon(player: String) =
        board.rowCompleted(player)
                || board.colCompleted(player)
                || board.diagonalCompleted(player)
}
