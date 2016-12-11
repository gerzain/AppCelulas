package com.developer.celulasdepaz.Interfaces;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.developer.celulasdepaz.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by Irving on 12/11/2016.
 */
public class FragmentoCapitalSocialDebilitado extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public static final String DEVELOPER_KEY = "AIzaSyBYEsTWpRP3IFKN88XuBoqvsKA4vluO08U";
    private static final String VIDEO_ID = "KVFDfm0DJ_8";
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    public YouTubePlayerView vista;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ambientes_familiares);
        vista = (YouTubePlayerView) findViewById(R.id.VideoAmbientes);
        vista.initialize(DEVELOPER_KEY, this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo(VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    "There was an error initializing the YouTubePlayer (%1$s)",
                    youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }
}
