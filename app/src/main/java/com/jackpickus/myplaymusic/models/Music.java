package com.jackpickus.myplaymusic.models;

import java.util.UUID;

public class Music {
    private UUID mId;
    private String title;
    private String artist;
    private String album;
    private Boolean favorite;
    private String data;

    public Music(String data) {
        mId = UUID.randomUUID();
        favorite = false;
        this.data = data;
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
        return this.favorite;
    }

    public void setFavorited(Boolean favorite) {
        this.favorite = favorite;
    }

    public String getData() {
        return data;
    }
}
