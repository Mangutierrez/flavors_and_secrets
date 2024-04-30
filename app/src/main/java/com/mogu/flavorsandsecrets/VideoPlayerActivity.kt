package com.mogu.flavorsandsecrets

import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class VideoPlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        val videoView: VideoView = findViewById(R.id.videoView)
        val videoUrl = intent.getStringExtra("VIDEO_URL")
        videoView.setVideoURI(Uri.parse(videoUrl))
        videoView.start()
    }
}
