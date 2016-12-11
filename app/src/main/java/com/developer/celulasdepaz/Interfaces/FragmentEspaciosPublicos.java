package com.developer.celulasdepaz.Interfaces;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.github.pedrovgs.DraggableListener;
import com.github.pedrovgs.DraggablePanel;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.developer.celulasdepaz.R;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Irving on 12/11/2016.
 */
public class FragmentEspaciosPublicos extends AppCompatActivity {

    public static final String DEVELOPER_KEY = "AIzaSyBYEsTWpRP3IFKN88XuBoqvsKA4vluO08U";
    private static final String VIDEO_ID = "8a5EXW9ACTM";
    public YouTubePlayerView vista;
    @InjectView(R.id.draggable_panel)
    DraggablePanel draggablePanel;
    @InjectView(R.id.iv_thumbnail)
    ImageView thumbnailImageView;
    private YouTubePlayer youtubePlayer;
    private YouTubePlayerSupportFragment youtubeFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.espacios_publicos);
        iniciarYoutube();
        initializeDraggablePanel();
        hookDraggablePanelListeners();

        ButterKnife.inject(this);


    }

    @OnClick(R.id.iv_thumbnail)
    void onContainerClick() {
        draggablePanel.maximize();
    }

    private void iniciarYoutube() {
        youtubeFragment = new YouTubePlayerSupportFragment();

        youtubeFragment.initialize(DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if (!b) {
                    youtubePlayer = youTubePlayer;
                    youtubePlayer.loadVideo(VIDEO_ID);
                    youtubePlayer.setShowFullscreenButton(true);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                if (youTubeInitializationResult.isUserRecoverableError()) {
                    //youTubeInitializationResult.getErrorDialog(getApplicationContext(), RECOVERY_DIALOG_REQUEST).show();
                } else {
                    String errorMessage = String.format(
                            "There was an error initializing the YouTubePlayer (%1$s)",
                            youTubeInitializationResult.toString());
                    Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initializeDraggablePanel() {
        // draggablePanel.setFragmentManager(getSupportFragmentManager());
        draggablePanel.setTopFragment(youtubeFragment);
        MoviePosterFragment moviePosterFragment = new MoviePosterFragment();
        moviePosterFragment.setPoster("fff");
        moviePosterFragment.setPosterTitle("gffgf");
        draggablePanel.setBottomFragment(moviePosterFragment);
        draggablePanel.initializeView();


    }

    private void hookDraggablePanelListeners() {
        draggablePanel.setDraggableListener(new DraggableListener() {
            @Override
            public void onMaximized() {
                playVideo();
            }

            @Override
            public void onMinimized() {

            }

            @Override
            public void onClosedToLeft() {
                pauseVideo();
            }

            @Override
            public void onClosedToRight() {
                pauseVideo();
            }
        });
    }

    private void pauseVideo() {
        if (youtubePlayer.isPlaying()) {
            youtubePlayer.pause();
        }
    }

    private void playVideo() {
        if (!youtubePlayer.isPlaying()) {
            youtubePlayer.play();
        }
    }

   /* @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError())
        {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        }
        else
        {
            String errorMessage = String.format(
                    "There was an error initializing the YouTubePlayer (%1$s)",
                    youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }


    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actividad_principal, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                return true;

            case R.id.action_carrito:

                Toast toast = Toast.makeText(getApplicationContext(), "Boton del perfil ", Toast.LENGTH_SHORT);
                toast.show();
                return true;


            case R.id.action_salir:
                this.finish();
                System.exit(0);
                break;


        }
        return super.onOptionsItemSelected(item);
    }

}
