package com.jackpickus.myplaymusic.activities;

import androidx.fragment.app.Fragment;

import com.jackpickus.myplaymusic.fragments.MusicFragment;

import java.util.UUID;

public class MusicActivity extends SingleFragmentActivity {

    private static final String EXTRA_MUSIC_ID =
            "com.pickustechnologies.myplaymusic.music_id";

    @Override
    protected Fragment createFragment() {
        UUID musicId = (UUID) getIntent().getSerializableExtra(EXTRA_MUSIC_ID);
        return MusicFragment.newInstance(musicId);
    }
}
