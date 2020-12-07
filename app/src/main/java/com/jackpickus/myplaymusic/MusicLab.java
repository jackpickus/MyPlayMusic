package com.jackpickus.myplaymusic;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class MusicLab {
    private static MusicLab sMusicLab;

    private List<Music> mMusics;

    public static MusicLab get(Context context) {
        if (sMusicLab == null) {
            sMusicLab = new MusicLab(context);
        }
        return sMusicLab;
    }

    private MusicLab(Context context) {
        mMusics = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Music m = new Music();
            m.setTitle("Song #" + i);
            mMusics.add(m);
        }
    }

    public List<Music> getMusics() {
        return mMusics;
    }

}
