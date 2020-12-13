package com.jackpickus.myplaymusic;

import java.util.UUID;

public class Music {
    private UUID mId;
    private String title;
    private String artist;
    private String album;
    private Boolean isFavorited;

    public Music() {
        mId = UUID.randomUUID();
        isFavorited = false;
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

    public Boolean getFavorited() {
        return isFavorited;
    }

    public void setFavorited(Boolean favorited) {
        isFavorited = favorited;
    }
}
