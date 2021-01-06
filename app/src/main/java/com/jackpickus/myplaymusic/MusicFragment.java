package com.jackpickus.myplaymusic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
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
    private ImageButton mRewindButton;
    private ImageButton mFastForwardButton;
    private MediaPlayer mMediaPlayer;
    private SeekBar mSeekBar;

    private OnSeekButtonClickListener mOnSeekButtonClickListener;

    private final Handler mHandler = new Handler();
    private final Runnable updateRunnablePosition = this::updatePosition;

    interface OnSeekButtonClickListener {
        void onSeekButtonClick(View view);
    }

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

        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
        }
        mMediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        try {
            mMediaPlayer.setDataSource(mMusic.getData());
            mMediaPlayer.prepare();
            mMediaPlayer.setLooping(false); // prevents song from playing indefinitely
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mOnSeekButtonClickListener = (OnSeekButtonClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Must implement onSeekButtonClickListener");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onResume() {
        super.onResume();


        if (mMediaPlayer == null) {
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
                mMediaPlayer.setLooping(false); // prevents song from playing indefinitely
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mPlayButton.setImageResource(R.drawable.ic_baseline_play_arrow_24);
        mSeekBar.setProgress(0);
        mMediaPlayer.stop();
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_item_about_song_artist) {
            Intent intent = ArtistInfoActivity.newIntent(getActivity(), mMusic.getArtist());
            startActivity(intent);
        } else if (id == R.id.menu_item_about_song_album) {
            //TODO start song's album activity
        } else if (id == R.id.menu_item_music_more_vert) {
            //TODO display song information
        }

        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        mSongTitleTextView = view.findViewById(R.id.song_title);
        mArtistTextView = view.findViewById(R.id.artist);

        mSeekBar = view.findViewById(R.id.song_playing_time);
        mSeekBar.setMax(mMediaPlayer.getDuration());

        mPlayButton = view.findViewById(R.id.play_image);
        mRewindButton = view.findViewById(R.id.rewind_image);
        mFastForwardButton = view.findViewById(R.id.fastforward_image);
        mLoveImageButton = view.findViewById(R.id.love_image_button);

        mFastForwardButton.setOnClickListener(v -> mOnSeekButtonClickListener.onSeekButtonClick(v));
        mRewindButton.setOnClickListener(v -> {
            if (mMediaPlayer.isPlaying() && mMediaPlayer.getCurrentPosition() > 2000) {
                mMediaPlayer.seekTo(0);
                mMediaPlayer.start();
                mSeekBar.setProgress(0);
            } else {
                mOnSeekButtonClickListener.onSeekButtonClick(v);
            }
        });


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
            if (mMediaPlayer.isPlaying()) {
                mPlayButton.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                mMediaPlayer.pause();
                mHandler.removeCallbacks(updateRunnablePosition);
            } else {
                mPlayButton.setImageResource(R.drawable.ic_baseline_pause_24);
                mMediaPlayer.start();
                updatePosition();
            }
        });

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mMediaPlayer != null) {
                    mMediaPlayer.seekTo(seekBar.getProgress());
                }
            }
        });

        return view;
    }

    private void updatePosition() {
        mHandler.removeCallbacks(updateRunnablePosition);
        if (mMediaPlayer != null) mSeekBar.setProgress(mMediaPlayer.getCurrentPosition());
        mHandler.postDelayed(updateRunnablePosition, 1500);
    }

}
