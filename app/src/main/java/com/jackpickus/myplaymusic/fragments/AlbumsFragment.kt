package com.jackpickus.myplaymusic.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jackpickus.myplaymusic.R
import com.jackpickus.myplaymusic.adapters.AlbumViewAdapter
import com.jackpickus.myplaymusic.models.Album

class AlbumsFragment : Fragment() {

    private var mContext: Context? = null
    private lateinit var mAlbumRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_music_list, container, false)

        mAlbumRecyclerView = view.findViewById(R.id.music_recycler_view)

        // Dumby Data to test album grid recycler view
        val album1 = Album(title = "Greatest Hits", artist = "Bob Marley", artwork = R.drawable.music_note)
        val album2 = Album(title = "album2", artist = "Artist 2", artwork = R.drawable.music_note)
        val album3 = Album(title = "album3", artist = "Artist 3", artwork = R.drawable.music_note)
        val album4 = Album(title = "Led Zepplin", artist = "Led Zepplin", artwork = R.drawable.music_note)
        val album5 = Album(title = "album5", artist = "Artist 3", artwork = R.drawable.music_note)
        val album6 = Album(title = "album6", artist = "Artist 2", artwork = R.drawable.music_note)
        val album7 = Album(title = "Man On The Moon", artist = "Kid Cudi", artwork = R.drawable.music_note)
        val album8 = Album(title = "10 Day", artist = "Chance The Rapper", artwork = R.drawable.music_note)
        val album9 = Album(title = "Blue Chips", artist = "Action Bronson", artwork = R.drawable.music_note)
        val album10 = Album(title = "album10", artist = "Artist 3", artwork = R.drawable.music_note)


        val albums = ArrayList<Album>()
        albums.add(album1)
        albums.add(album2)
        albums.add(album3)
        albums.add(album4)
        albums.add(album5)
        albums.add(album6)
        albums.add(album7)
        albums.add(album8)
        albums.add(album9)
        albums.add(album10)

        val mAdapter = AlbumViewAdapter(albums) { album -> albumClicked(album) }

        mAlbumRecyclerView.layoutManager = GridLayoutManager(mContext, 2)
        mAlbumRecyclerView.adapter = mAdapter

        return view
    }

    private fun albumClicked(album: Album) {
        Toast.makeText(mContext, "Album " + album.title + " clicked!", Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onDetach() {
        super.onDetach()
        mContext = null
    }

//    private fun getAlbums() : ArrayList<String> {
//        val projection = arrayOf(MediaStore.Audio.Albums._ID, MediaStore.Audio.Albums.ALBUM, MediaStore.Audio.Albums.ARTIST, MediaStore.Audio.Albums.NUMBER_OF_SONGS)
//        val selection: String? = null
//        val selectionArgs: Array<String>? = null
//        val sortOrder: String = MediaStore.Audio.Media.ALBUM + " ASC"
//
//        val cursor: Cursor? = mContext?.applicationContext?.contentResolver?.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, projection, selection, selectionArgs, sortOrder)
//
//        while (cursor!!.moveToNext()) {
//            val data = cursor.getString(4)
//            val m = Music(data)
//            m.album = cursor.getString(3)
//            m.title = cursor.getString(2)
//            m.artist = cursor.getString(1)
//            songs.add(m)
//        }
//
//        cursor.close()
//
//    }
}