package com.jackpickus.myplaymusic;
import java.util.UUID;

public class Favorite {

    private String mId;
    private Boolean isFavorite;

    public Favorite() {
        isFavorite = false;
        mId = UUID.randomUUID().toString();
    }

    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Boolean favorite) {
        isFavorite = favorite;
    }
}
