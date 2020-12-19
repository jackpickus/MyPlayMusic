package com.jackpickus.myplaymusic;

import com.jackpickus.myplaymusic.models.Music;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MusicUnitTest {

    private Music music;

    @Before
    public void beforeMusicTests() {
        music = new Music();
        music.setArtist("The Beatles");
        music.setAlbum("Sargent Peppers Lonely Hears Club Band");
        music.setTitle("A Day in a Life");
        music.setFavorited(true);
    }

    @Test
    public void getAlbum() {
        assertEquals(music.getAlbum(), "Sargent Peppers Lonely Hears Club Band");
    }

    @Test
    public void getTitle() {
        assertEquals(music.getTitle(), "A Day in a Life");
    }

    @Test
    public void getArtist() {
        assertEquals(music.getArtist(), "The Beatles");
    }

}
