package com.jackpickus.myplaymusic;

import androidx.fragment.app.Fragment;

public class MusicListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new MusicListFragment();
    }
}
