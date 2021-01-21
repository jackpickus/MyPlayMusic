package com.jackpickus.myplaymusic.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.jackpickus.myplaymusic.R;
import com.jackpickus.myplaymusic.fragments.MusicFragment;
import com.jackpickus.myplaymusic.fragments.MusicListFragment;
import com.jackpickus.myplaymusic.models.Music;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MusicPagerActivity extends AppCompatActivity implements MusicFragment.OnSeekButtonClickListener {
    private static final String EXTRA_MUSIC_ID =
            "com.jackpickus.myplaymusic.music_id";
    private static final String EXTRA_MUSIC_ARTIST =
            "com.jackpickus.myplaymusic.music_queue";

    private ViewPager2 mViewPager2;
    private List<Music> mMusics;
    private String artist;
    private List<Music> otherMusicQueue;

    public static Intent newIntent(Context packageContext, UUID musicId, String artist) {
        Intent intent = new Intent(packageContext, MusicPagerActivity.class);
        intent.putExtra(EXTRA_MUSIC_ID, musicId);
        intent.putExtra(EXTRA_MUSIC_ARTIST, artist);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_pager);

        UUID musicId = (UUID) getIntent().getSerializableExtra(EXTRA_MUSIC_ID);

        mViewPager2 = findViewById(R.id.activity_music_pager_view_pager);

        mMusics = MusicListFragment.newMusics;

        artist = (String) getIntent().getSerializableExtra(EXTRA_MUSIC_ARTIST);

        otherMusicQueue = new ArrayList<>();

        // if this is true, means user is not in music list fragment (the entire lib of music)
        // they are in the artist fragment or album one
        if (!artist.equals(";")) {
            for (Music m : mMusics) {
                if (m.getArtist().equals(artist)) {
                    otherMusicQueue.add(m);
                }
            }
            mMusics = otherMusicQueue;
        }


        mViewPager2.setAdapter(new MusicSlidePagerAdapter(this));

        for (int i = 0; i < mMusics.size(); i++) {
            if (mMusics.get(i).getId().equals(musicId)) {
                mViewPager2.setCurrentItem(i);
                break;
            }
        }

    }

    @Override
    public void onSeekButtonClick(View view) {
        int currentPosition = mViewPager2.getCurrentItem();

        int id = view.getId();

        if (!(currentPosition <= 0) && id == R.id.rewind_image) {
            mViewPager2.setCurrentItem(currentPosition - 1);
        } else if (!(currentPosition >= (mMusics.size() - 1)) && id == R.id.fastforward_image) {
            mViewPager2.setCurrentItem(currentPosition + 1);
        }

    }

    private class MusicSlidePagerAdapter extends FragmentStateAdapter {
        public MusicSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Music music = mMusics.get(position);
            return MusicFragment.newInstance(music.getId());
        }

        @Override
        public int getItemCount() {
            return mMusics.size();
        }
    }

}
