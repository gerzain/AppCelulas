package com.developer.celulasdepaz.Interfaces;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.developer.celulasdepaz.R;

/**
 * Created by Irving on 15/11/2016.
 */
public class MoviePosterFragment extends Fragment {
    @InjectView(R.id.iv_thumbnail)
    ImageView thumbnailImageView;

    private String videoPosterThumbnail;
    private String posterTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_, container, false);
        ButterKnife.inject(this, view);
        Picasso.with(getActivity())
                .load(videoPosterThumbnail)
                .placeholder(R.drawable.abono)
                .into(thumbnailImageView);
        return view;
    }

    public void setPoster(String videoPosterThumbnail) {
        this.videoPosterThumbnail = videoPosterThumbnail;
    }

    public void setPosterTitle(String posterTitle) {
        this.posterTitle = posterTitle;
    }

    @OnClick(R.id.iv_thumbnail)
    void onThubmnailClicked() {
        Toast.makeText(getActivity(), posterTitle, Toast.LENGTH_SHORT).show();
    }


}
