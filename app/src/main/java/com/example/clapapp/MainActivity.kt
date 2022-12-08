package com.example.clapapp

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var seekBar: SeekBar
    private var mediaPlayer: MediaPlayer? = null
    // runnable is an interface that can be used
    // to run a function in a thread separately from the main thread
    private lateinit var runnable: Runnable
    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*  val button = findViewById<android.widget.Button>(R.id.btnClap)
          button.setOnClickListener {
              mediaPlayer?.start()
              println("Clap")
          }*/
        seekBar = findViewById(R.id.seekBar)
        handler = Handler(Looper.getMainLooper())
        val btn_play = findViewById<FloatingActionButton>(R.id.btn_play)
        val btn_pause = findViewById<FloatingActionButton>(R.id.btn_pause)
        val btn_stop = findViewById<FloatingActionButton>(R.id.btn_stop)

        btn_play.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.music)
                initSeekBar()
            }
            mediaPlayer?.start()
        }
        btn_pause.setOnClickListener {
            mediaPlayer?.pause()
        }
        btn_stop.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            mediaPlayer?.release()
            mediaPlayer = null
            // remove the runnable from the handler
            handler.removeCallbacks(runnable)
            // this will set the seekbar to 0
            seekBar.progress = 0
        }
    }

    private fun initSeekBar() {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) mediaPlayer?.seekTo(progress)

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        val tvPlayed = findViewById<TextView>(R.id.tcPlayed)
        val tvDue = findViewById<TextView>(R.id.tvDue)
        seekBar.max= mediaPlayer!!.duration

        runnable = Runnable {
            seekBar.progress = mediaPlayer!!.currentPosition
            val playedTime = mediaPlayer!!.currentPosition/1000
            tvPlayed.text = "$playedTime sec"
            val duration = mediaPlayer!!.duration/1000
            tvDue.text = "${duration - playedTime} sec"
            // this will add the thread to the queue
            // and will run the thread after 1000 milliseconds
            handler.postDelayed(runnable, 1000)

        }

        handler.postDelayed(runnable, 1000)

    }

}