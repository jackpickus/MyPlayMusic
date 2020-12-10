package com.jackpickus.myplaymusic;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/*
 * Finds songs stored on phone and returns a list
 */
public class SongsManager {

    String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";

    String[] projection = {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DISPLAY_NAME
    };

    public List<String> getSongs(Context context) {

        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                MediaStore.Audio.Media.TITLE + " ASC");

        List<String> songs = new ArrayList<>();
        while(true) {
            assert cursor != null;
            if (!cursor.moveToNext()) break;
            songs.add(cursor.getString(0) + "||" + cursor.getString(1) + "||" + cursor.getString(2) + "||" + cursor.getString(3) + "||" + cursor.getString(4));
        }

        cursor.close();
        return songs;
    }



}
