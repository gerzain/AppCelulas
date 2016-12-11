package com.developer.celulasdepaz.VideoActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.developer.celulasdepaz.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

public class EspaciosPublicos extends AppCompatActivity {

    private static final String YOUTUBE_API_KEY = "AIzaSyBYEsTWpRP3IFKN88XuBoqvsKA4vluO08U";
    private static final String VIDEO_KEY = "8a5EXW9ACTM";
    private YouTubePlayer youtubePlayer;
    private YouTubePlayerSupportFragment youtubeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espacios_publicos3);
        iniciarYoutube();
    }

    private void iniciarYoutube() {
        youtubeFragment = new YouTubePlayerSupportFragment();
        youtubeFragment.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if (!b) {
                    youtubePlayer = youTubePlayer;
                    youtubePlayer.loadVideo(VIDEO_KEY);
                    youtubePlayer.setShowFullscreenButton(false);
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);

                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

    }
}
