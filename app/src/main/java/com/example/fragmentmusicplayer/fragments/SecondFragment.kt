package com.example.fragmentmusicplayer.fragments

import android.content.Context.AUDIO_SERVICE
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fragmentmusicplayer.R

class SecondFragment: Fragment(R.layout.second_fragment) {

    private lateinit var runnable: Runnable
    private var handler = Handler(Looper.getMainLooper())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val seekbar: SeekBar = view.findViewById(R.id.seekbar)
        val playButton: Button = view.findViewById(R.id.play)
        val mediaPlayer = MediaPlayer.create(context,R.raw.led_zeppelin_black_dog)
        val name: TextView = view.findViewById(R.id.textView)
        val photo: ImageView = view.findViewById(R.id.imageView)
        val playedTime: TextView = view.findViewById(R.id.playedTime)
        val remainingTime: TextView = view.findViewById(R.id.leftTime)
        val preButton5: Button = view.findViewById(R.id.previous5)
        val nxtButton5: Button = view.findViewById(R.id.next5)
        val preButton10: Button = view.findViewById(R.id.previous10)
        val nxtButton10: Button = view.findViewById(R.id.next10)
        val volumeSeekBar: SeekBar = view.findViewById(R.id.seekBar)
        val lowVolumeImage: ImageView = view.findViewById(R.id.imageView2)
        val highVolumeImage: ImageView = view.findViewById(R.id.imageView3)




        mediaPlayer.start()
        playButton.setBackgroundResource(R.drawable.ic_baseline_pause_circle_filled_24)




        seekbar.progress = 0
        seekbar.max = mediaPlayer.duration

        photo.setImageDrawable(null)
        photo.setBackgroundResource(R.drawable.led_zeppelin_black_dog)

        name.setSingleLine()
        name.isSelected = true
        name.text = "led___zeppelin___black___dog"


        val audioManager = requireActivity().getSystemService(AUDIO_SERVICE) as AudioManager

        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        volumeSeekBar.max = maxVolume

        val currVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        volumeSeekBar.progress = currVolume



        volumeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, p1,0)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })


        preButton5.setOnClickListener {
            mediaPlayer.seekTo(mediaPlayer.currentPosition - 5000)
        }
        nxtButton5.setOnClickListener {
            mediaPlayer.seekTo(mediaPlayer.currentPosition + 5000)
        }
        preButton10.setOnClickListener {
            mediaPlayer.seekTo(mediaPlayer.currentPosition - 10000)
        }
        nxtButton10.setOnClickListener {
            mediaPlayer.seekTo(mediaPlayer.currentPosition + 10000)
        }


        playButton.setOnClickListener {
            if (!mediaPlayer.isPlaying){
                mediaPlayer.start()
                playButton.setBackgroundResource(R.drawable.ic_baseline_pause_circle_filled_24)
            }
            else{
                mediaPlayer.pause()
                playButton.setBackgroundResource(R.drawable.ic_baseline_play_circle_filled_24)
            }
        }


        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p2){
                    mediaPlayer.seekTo(p1)

                    var p1S = p1 / 1000
                    var p1Min = (p1S / 60)
                    var p1Sec = (p1S % 60)

                    playedTime.text = "${p1Min}:${p1Sec}"
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

        runnable = Runnable {

            seekbar.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable,0)


            volumeSeekBar.progress = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)

            if (volumeSeekBar.progress > volumeSeekBar.max / 2){
                highVolumeImage.setBackgroundResource(R.drawable.ic_baseline_volume_up_24)
            }
            else if (volumeSeekBar.progress <= volumeSeekBar.max / 2 && volumeSeekBar.progress > 0){
                highVolumeImage.setImageDrawable(null)
                highVolumeImage.setBackgroundResource(R.drawable.ic_baseline_volume_down_24)
            }
            else if (volumeSeekBar.progress == 0){
                highVolumeImage.setBackgroundResource(R.drawable.ic_baseline_volume_off_24)
            }


            val remTimeSec = ((mediaPlayer.duration / 1000) - (mediaPlayer.currentPosition / 1000))

            playedTime.text = (((mediaPlayer.currentPosition / 1000) - ((mediaPlayer.currentPosition / 1000) % 60)) / 60).toString() + ":" + ((mediaPlayer.currentPosition / 1000) % 60).toString()
            remainingTime.text = "-" + ((remTimeSec - (remTimeSec % 60)) / 60).toString() + ":" + (((mediaPlayer.duration / 1000) - (mediaPlayer.currentPosition / 1000)) % 60).toString()

        }
        handler.postDelayed(runnable,0)

        mediaPlayer.setOnCompletionListener {

            mediaPlayer.start()
            playButton.setBackgroundResource(R.drawable.ic_baseline_pause_circle_filled_24)

        }
    }
}