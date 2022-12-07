package com.example.clapapp

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private var mediaPlayer : MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


      /*  val button = findViewById<android.widget.Button>(R.id.btnClap)
        button.setOnClickListener {
            mediaPlayer?.start()
            println("Clap")
        }*/

        val btn_play = findViewById<FloatingActionButton>(R.id.btn_play)
        val btn_pause = findViewById<FloatingActionButton>(R.id.btn_pause)
        val btn_stop = findViewById<FloatingActionButton>(R.id.btn_stop)

        btn_play.setOnClickListener {
            if(mediaPlayer != null){
                mediaPlayer = MediaPlayer.create(this, R.raw.music)
            }
            mediaPlayer?.start()
        }
        btn_pause.setOnClickListener {
            mediaPlayer?.pause()
        }
        btn_stop.setOnClickListener {
            mediaPlayer?.stop()
            // initialize the media player again
            mediaPlayer = MediaPlayer.create(this, R.raw.music)
        }
    }
}