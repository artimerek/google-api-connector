package pl.artimerek.google_api_connector

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val YT_VIDEO_ID = "z7lutJdxQYw"
const val YT_PLAYLIST_ID = "PLa2kwLIoLbrd2Yw6d8aFNRsNqPeU2HaGQ"

class PlayerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = layoutInflater.inflate(R.layout.activity_player, null) as ConstraintLayout
        setContentView(layout)

        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)

        layout.addView(playerView)
        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)

    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        ytPlayer: YouTubePlayer?,
        wasRestored: Boolean
    ) {
       Toast.makeText(this, "Init success", Toast.LENGTH_SHORT).show()

        ytPlayer?.setPlayerStateChangeListener(playerStateChangeListener)
        ytPlayer?.setPlaybackEventListener(playbackEventListener)

        if (!wasRestored){
            ytPlayer?.cueVideo(YT_VIDEO_ID)
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        initResult: YouTubeInitializationResult?
    ) {
        val REQUEST_CODE = 0

        if (initResult?.isUserRecoverableError == true){
            initResult.getErrorDialog(this, REQUEST_CODE).show()
        }else{
            val errorMessage = "Init error $initResult"
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    private val playbackEventListener = object: YouTubePlayer.PlaybackEventListener{
        override fun onPlaying() {
            Toast.makeText(this@PlayerActivity, "Good, video is playing", Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
            Toast.makeText(this@PlayerActivity, "Video has paused", Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {

        }

        override fun onBuffering(p0: Boolean) {

        }

        override fun onSeekTo(p0: Int) {

        }
    }

    private val playerStateChangeListener = object: YouTubePlayer.PlayerStateChangeListener{
        override fun onLoading() {

        }

        override fun onLoaded(p0: String?) {

        }

        override fun onAdStarted() {
            Toast.makeText(this@PlayerActivity, "Ad", Toast.LENGTH_SHORT).show()
        }

        override fun onVideoStarted() {
            Toast.makeText(this@PlayerActivity, "Started", Toast.LENGTH_SHORT).show()
        }

        override fun onVideoEnded() {
            Toast.makeText(this@PlayerActivity, "Video completed", Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {

        }
    }
}