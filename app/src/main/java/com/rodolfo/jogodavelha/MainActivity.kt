package com.rodolfo.jogodavelha

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun btnClick (view : View) {
        val btnSelecionado = view as Button
        var cellId = 0

        when(btnSelecionado.id) {
            R.id.button1 -> cellId = 1
            R.id.button2 -> cellId = 2
            R.id.button3 -> cellId = 3
            R.id.button4 -> cellId = 4
            R.id.button5 -> cellId = 5
            R.id.button6 -> cellId = 6
            R.id.button7 -> cellId = 7
            R.id.button8 -> cellId = 8
            R.id.button9 -> cellId = 9
        }

//        Toast.makeText(this, "ID: $cellId", Toast.LENGTH_SHORT).show()
        playGame(cellId, btnSelecionado)
    }

    private var player1 = ArrayList<Int>()
    private var player2 = ArrayList<Int>()

    private var activePlayer = 1
    private var countJogadas : Int = 0

    private fun playGame(cellId : Int, btnSelecionado : Button) {
        if (activePlayer == 1) {
            btnSelecionado.text = "X"
            btnSelecionado.textSize = 20F
            btnSelecionado.setTextColor(Color.WHITE)
            btnSelecionado.setBackgroundColor(Color.BLUE)
            player1.add(cellId)
            activePlayer = 2
        } else {
            btnSelecionado.text = "O"
            btnSelecionado.textSize = 20F
            btnSelecionado.setTextColor(Color.WHITE)
            btnSelecionado.setBackgroundColor(Color.MAGENTA)
            player2.add(cellId)
            activePlayer = 1
        }

        btnSelecionado.isEnabled = false

        countJogadas++

        Thread.sleep(1_000)  // wait for 1 second

        checkWinner()
    }

    private fun checkWinner() {
        var winner = -1

        // linha 1
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner = 2
        }

        // linha 2
        if(player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner = 1
        }
        if(player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner = 2
        }

        // linha 3
        if(player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner = 2
        }

        // coluna 1
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner = 2
        }

        // coluna 2
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner = 1
        }
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner = 2
        }

        // coluna 3
        if(player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner = 2
        }

        // diagonal 1
        if(player1.contains(1) && player1.contains(5) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(5) && player2.contains(9)){
            winner = 2
        }

        // diagonal 2
        if(player1.contains(3) && player1.contains(5) && player1.contains(7)){
            winner = 1
        }
        if(player2.contains(3) && player2.contains(5) && player2.contains(7)){
            winner = 2
        }

        if (winner != -1) {
            if (winner == 1) {
                Toast.makeText(this, "Player 1 winner the game!", Toast.LENGTH_SHORT).show()
                SoundEffects.playWins(this)
                iniciarJogo()
            } else {
                Toast.makeText(this, "Player 2 winner the game!", Toast.LENGTH_SHORT).show()
                SoundEffects.playWins(this)

                iniciarJogo()
            }
        }

        if (countJogadas == 9) {
            Toast.makeText(this, "Ninguém ganhou!", Toast.LENGTH_SHORT).show()
            SoundEffects.playNoWins(this)
            iniciarJogo()
        }
    }

    private fun iniciarJogo() {
        SoundEffects.playNewGame(this)

//        val it = Intent(this, MainActivity::class.java)

//        finishAfterTransition()
//        startActivity(it, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())

//        overrideActivityTransition(0,0,0)
        finish()
        overridePendingTransition(0, 0)
        // Showing a toast message at the time when we are capturing screenshot
        Toast.makeText(
            this@MainActivity,
            "Restart the game", Toast.LENGTH_SHORT
        ).show()
    }
}