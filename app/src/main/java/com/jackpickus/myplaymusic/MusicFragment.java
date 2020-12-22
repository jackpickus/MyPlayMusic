package com.jackpickus.myplaymusic;

import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Build;
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
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.jackpickus.myplaymusic.models.Music;

import java.io.IOException;
import java.util.UUID;

public class MusicFragment extends Fragment {
    private static final String ARG_MUSIC_ID = "music_id";

    private Music mMusic;
    private TextView mSongTitleTextView;
    private TextView mArtistTextView;
    private ImageButton mLoveImageButton;
    private ImageButton mPlayButton;
    private MediaPlayer mMediaPlayer;

    public static MusicFragment newInstance(UUID musicId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_MUSIC_ID, musicId);

        MusicFragment fragment = new MusicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        UUID musicId = (UUID) getArguments().getSerializable(ARG_MUSIC_ID);

        mMusic = findMusicId(musicId);

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        try {
            mMediaPlayer.setDataSource(mMusic.getData());
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        mMediaPlayer.release();
        mMediaPlayer = null;
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

        mPlayButton = view.findViewById(R.id.play_image);
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

        mPlayButton.setOnClickListener(v -> {
            if(mMediaPlayer.isPlaying()){
                mPlayButton.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                mMediaPlayer.pause();
            } else {
                mPlayButton.setImageResource(R.drawable.ic_baseline_pause_24);
                mMediaPlayer.start();
            }
        });

        return view;
    }
}
