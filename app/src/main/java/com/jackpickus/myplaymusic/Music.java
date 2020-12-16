package com.jackpickus.myplaymusic;

import java.util.UUID;

public class Music {
    private UUID mId;
    private String title;
    private String artist;
    private String album;
    private Favorite favorite;

    public Music(Favorite favorite) {
        mId = UUID.randomUUID();
        this.favorite = favorite;
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
        return this.favorite.getIsFavorite();
    }

    public void setFavorited(Boolean favorited) {
        this.favorite.setIsFavorite(favorited);
    }
}
