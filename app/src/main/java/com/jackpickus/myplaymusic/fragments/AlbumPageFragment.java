package com.jackpickus.myplaymusic.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.jackpickus.myplaymusic.R;

public class AlbumPageFragment extends Fragment {

    private ImageView mAlbumArt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  (ViewGroup) inflater.inflate(R.layout.album_item, container, false);

        mAlbumArt = view.findViewById(R.id.album_art_image_view);
        mAlbumArt.setImageResource(R.drawable.music_note);

        return view;
    }
}

