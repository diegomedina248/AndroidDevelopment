package com.diegomedina.tictactoe

class Board {
    private val board = arrayOf<Array<String?>>(
        Array(3) { null },
        Array(3) { null },
        Array(3) { null }
    )

    fun makeMove(player: String, row: Int, col: Int) {
        board[row][col] = player
    }

    fun rowCompleted(player: String): Boolean {
        for (row in board.indices) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) return true
        }

        return false
    }

    fun colCompleted(player: String): Boolean {
        for (col in board.indices) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) return true
        }

        return false
    }

    fun diagonalCompleted(player: String) = (board[0][0] == player && board[1][1] == player && board[2][2] == player)
            || (board[0][2] == player && board[1][1] == player && board[2][0] == player)

    fun hasEmptyTile(): Boolean {
        for (row in board.indices) {
            for (col in board[row].indices) {
                if (board[row][col] == null) return true
            }
        }

        return false
    }
}
