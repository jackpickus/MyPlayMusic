package com.jackpickus.myplaymusic.activities;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.jackpickus.myplaymusic.fragments.MusicFragment;

import java.util.UUID;

public class MusicActivity extends SingleFragmentActivity {
    private static final String EXTRA_MUSIC_ID =
            "com.jackpickus.myplaymusic.music_id";
    private static final String EXTRA_MUSIC_ARTIST =
            "com.jackpickus.myplaymusic.music_queue";

    public static Intent newIntent(Context packageContext, UUID id) {
        Intent intent = new Intent(packageContext, MusicActivity.class);
        intent.putExtra(EXTRA_MUSIC_ID, id);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID musicId = (UUID) getIntent().getSerializableExtra(EXTRA_MUSIC_ID);
        return MusicFragment.newInstance(musicId);
    }
}
