package pl.artimerek.google_api_connector

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeStandalonePlayer
import kotlinx.android.synthetic.main.activity_standalone.*
import java.lang.IllegalArgumentException


class StandaloneActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standalone)

        buttonPlayVideo.setOnClickListener(this)
        buttonPlaylist.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val intent = when (view?.id) {
            R.id.buttonPlayVideo -> YouTubeStandalonePlayer.createVideoIntent(
                this, getString(R.string.GOOGLE_API_KEY), YT_VIDEO_ID, 0, true, false
            )
            R.id.buttonPlaylist -> YouTubeStandalonePlayer.createPlaylistIntent(
                this, getString(R.string.GOOGLE_API_KEY), YT_PLAYLIST_ID, 0, 0, true, false
            )
            else -> throw IllegalArgumentException("Undefined button clicked")
        }
        startActivity(intent)
    }
}