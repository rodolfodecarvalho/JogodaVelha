package com.rodolfo.jogodavelha

import android.content.Context
import android.media.MediaPlayer

class SoundEffects {

    companion object {
        fun playNoWins(context: Context){
            val mp: MediaPlayer = MediaPlayer.create(context, R.raw.nowins)
        }

        fun playWins(context: Context) {
            val mp : MediaPlayer = MediaPlayer.create(context, R.raw.wins)
            mp.start()
        }

        fun playNewGame(context: Context) {
            val mp : MediaPlayer = MediaPlayer.create(context, R.raw.newgame)
            mp.start()
        }
    }
}