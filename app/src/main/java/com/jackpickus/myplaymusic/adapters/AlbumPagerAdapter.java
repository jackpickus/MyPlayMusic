package com.jackpickus.myplaymusic.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.jackpickus.myplaymusic.fragments.AlbumPageFragment;
import com.jackpickus.myplaymusic.fragments.MusicListFragment;

public class AlbumPagerAdapter extends FragmentStateAdapter {
    public AlbumPagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        //TODO pass the album art to be displayed
        return new AlbumPageFragment();
    }

    @Override
    public int getItemCount() {
        //TODO get number of items in the view pager
        return MusicListFragment.newMusics.size();
    }
}