package com.jackpickus.myplaymusic;

import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/*
 * Finds songs stored on phone and returns a list
 */
public class SongsManager {

//    String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
//
//    String[] projection = {
//            MediaStore.Audio.Media._ID,
//            MediaStore.Audio.Media.ARTIST,
//            MediaStore.Audio.Media.TITLE,
//            MediaStore.Audio.Media.DATA,
//            MediaStore.Audio.Media.DISPLAY_NAME,
//            MediaStore.Audio.Media.DURATION
//    };
//
//    Cursor cursor = this.managedQuery(
//            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
//            projection,
//            selection,
//            null,
//            null
//    );
//
//    private List<String> songs = new ArrayList<String>();
//    while(cursor.moveToNext())
//
//    {
//        songs.add(cursor.getString(0) + "||" + cursor.getString(1) + "||" + cursor.getString(2) + "||" + cursor.getString(3) + "||" + cursor.getString(4) + "||" + cursor.getString(5));
//    }

}
