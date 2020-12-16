package com.jackpickus.myplaymusic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FavoriteUnitTest {

    Favorite fav;

    @Before
    public void beforeTests() {
        fav = new Favorite();
    }

    @Test
    public void myFavoriteIsFalse() {
        assertFalse(fav.getIsFavorite());
    }

    @Test
    public void setFavoriteTrue() {
        fav.setIsFavorite(true);
        assertTrue(fav.getIsFavorite());
    }
}