package com.jackpickus.myplaymusic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void myFavoriteIsFalse() {
        Favorite fav = new Favorite();
        assertFalse(fav.getIsFavorite());
    }

    @Test
    public void setFavoriteTrue() {
        Favorite favorite = new Favorite();
        favorite.setIsFavorite(true);
        assertTrue(favorite.getIsFavorite());
    }
}