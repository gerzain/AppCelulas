package com.developer.celulasdepaz.Videos.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.developer.celulasdepaz.Interfaces.FragmentoAcerca;
import com.developer.celulasdepaz.R;
import com.github.pedrovgs.DraggableListener;
import com.github.pedrovgs.DraggablePanel;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Irving on 29/11/2016.
 */
public class MarginacionExlusionSocial extends Fragment implements YouTubePlayer.PlayerStateChangeListener {
    private static final String YOUTUBE_API_KEY = "AIzaSyBYEsTWpRP3IFKN88XuBoqvsKA4vluO08U";
    private static final String VIDEO_KEY = "X677YHtlHxg";
    @InjectView(R.id.iv_thumbnail)
    ImageView thumbnailImageView;
    @InjectView(R.id.draggable_panel)
    DraggablePanel draggablePanel;
    private YouTubePlayer youtubePlayer;
    private YouTubePlayerSupportFragment youtubeFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.espacios_public, container, false);
        ButterKnife.inject(this, view);
        iniciarYoutube();
        initializeDraggablePanel();

        hookDraggablePanelListeners();
        return view;

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
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

    }// Termina el metodo de inicio para YouTube

    private void initializeDraggablePanel() {
        draggablePanel.setFragmentManager(getActivity().getSupportFragmentManager());
        draggablePanel.setTopFragment(youtubeFragment);
        FragmentoAcerca moviePosterFragment = new FragmentoAcerca();
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
                // pauseVideo();
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


    @Override
    public void onLoading() {

    }

    @Override
    public void onLoaded(String s) {

    }

    @Override
    public void onAdStarted() {

    }

    @Override
    public void onVideoStarted() {

    }

    @Override
    public void onVideoEnded() {

    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {

    }
}
