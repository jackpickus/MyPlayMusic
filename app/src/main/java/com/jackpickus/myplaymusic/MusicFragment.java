package com.jackpickus.myplaymusic;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.UUID;

public class MusicFragment extends Fragment {
    private static final String ARG_MUSIC_ID = "music_id";

    private Music mMusic;
    private TextView mSongTitleTextView;
    private TextView mArtistTextView;

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

        mMusic = MusicLab.get(getActivity()).getMusic(musicId);
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

        mSongTitleTextView.setText(mMusic.getTitle());
        mArtistTextView.setText(mMusic.getArtist());

        return view;
    }
}
