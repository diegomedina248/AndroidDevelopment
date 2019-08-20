package com.diegomedina.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var game = Game()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button00 = findViewById<Button>(R.id.button00)
        val button01 = findViewById<Button>(R.id.button01)
        val button02 = findViewById<Button>(R.id.button02)

        val button10 = findViewById<Button>(R.id.button10)
        val button11 = findViewById<Button>(R.id.button11)
        val button12 = findViewById<Button>(R.id.button12)

        val button20 = findViewById<Button>(R.id.button20)
        val button21 = findViewById<Button>(R.id.button21)
        val button22 = findViewById<Button>(R.id.button22)

        button00.setOnClickListener { handleClick(button00, 0, 0) }
        button01.setOnClickListener { handleClick(button01, 0, 1) }
        button02.setOnClickListener { handleClick(button02, 0, 2) }

        button10.setOnClickListener { handleClick(button10, 1, 0) }
        button11.setOnClickListener { handleClick(button11, 1, 1) }
        button12.setOnClickListener { handleClick(button12, 1, 2) }

        button20.setOnClickListener { handleClick(button20, 2, 0) }
        button21.setOnClickListener { handleClick(button21, 2, 1) }
        button22.setOnClickListener { handleClick(button22, 2, 2) }
    }

    private fun handleClick(button: Button, row: Int, col: Int) {
        button.text = game.player
        button.isEnabled = false

        game.makeMove(row, col)
        checkStatus()
    }

    private fun checkStatus() {
        val textResult = findViewById<TextView>(R.id.textResult)

        val playerWon = game.playerWon()
        if (playerWon != null) {
            textResult.visibility = View.VISIBLE
            textResult.text = getString(R.string.player_won)
        }

        if (game.draw()) {
            textResult.visibility = View.VISIBLE
            textResult.text = "Draw!"
        }
    }
}
