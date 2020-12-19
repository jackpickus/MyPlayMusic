package com.jackpickus.myplaymusic;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jackpickus.myplaymusic.models.Music;

import java.util.UUID;

public class MusicFragment extends Fragment {
    private static final String ARG_MUSIC_ID = "music_id";

    private Music mMusic;
    private TextView mSongTitleTextView;
    private TextView mArtistTextView;
    private ImageButton mLoveImageButton;

    public static MusicFragment newInstance(UUID musicId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_MUSIC_ID, musicId);

        MusicFragment fragment = new MusicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        UUID musicId = (UUID) getArguments().getSerializable(ARG_MUSIC_ID);

        mMusic = findMusicId(musicId);
    }

    private Music findMusicId(UUID id) {
        for (Music m : MusicListFragment.newMusics) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_music, menu);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        mSongTitleTextView = view.findViewById(R.id.song_title);
        mArtistTextView = view.findViewById(R.id.artist);

        mLoveImageButton = view.findViewById(R.id.love_image_button);
        if (mMusic.getFavorited()) {
            mLoveImageButton.setColorFilter(Color.RED);
        }

        mSongTitleTextView.setText(mMusic.getTitle());
        mArtistTextView.setText(mMusic.getArtist());

        mLoveImageButton.setOnClickListener(v -> {
            mLoveImageButton.setSelected(!mLoveImageButton.isSelected());

            if (mLoveImageButton.isSelected()) {
                mLoveImageButton.setColorFilter(Color.RED);
                mMusic.setFavorited(true);
            } else {
                mLoveImageButton.setColorFilter(Color.argb(255, 52, 52, 52));
                mMusic.setFavorited(false);
            }

        });

        return view;
    }
}
