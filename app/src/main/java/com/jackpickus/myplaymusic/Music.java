package com.jackpickus.myplaymusic;

import java.util.UUID;

public class Music {
    private UUID mId;
    private String title;
    private String artist;
    private String album;

    public Music() {
        mId = UUID.randomUUID();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public UUID getId() {
        return mId;
    }
}
