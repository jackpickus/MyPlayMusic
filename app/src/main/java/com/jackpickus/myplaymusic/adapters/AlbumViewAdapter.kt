package com.jackpickus.myplaymusic.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jackpickus.myplaymusic.R
import com.jackpickus.myplaymusic.models.Album

class AlbumViewAdapter(private val albumDataSet: ArrayList<Album>, private val onClick:(Album) -> Unit) :
    RecyclerView.Adapter<AlbumViewAdapter.AlbumViewHolder>() {

        class AlbumViewHolder(view: View, val onClick: (Album) -> Unit) : RecyclerView.ViewHolder(view) {
            private val mAlbumTitleTextView: TextView
            private val mAlbumArtistTextView: TextView
            private val mAlbumArtImageView: ImageView
            private var currentAlbum: Album? = null

            init {
                view.setOnClickListener {
                    currentAlbum?.let {
                        onClick(it)
                    }
                }

                mAlbumTitleTextView = view.findViewById(R.id.album_title_text_view)
                mAlbumArtistTextView = view.findViewById(R.id.album_artist_text_view)
                mAlbumArtImageView = view.findViewById(R.id.album_artwork_image_view)

            }

            fun bindAlbum(album: Album) {
                currentAlbum = album
                mAlbumTitleTextView.text = album.title
                mAlbumArtistTextView.text = album.artist
                mAlbumArtImageView.setImageResource(album.artwork)
            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_album, parent, false)

        return AlbumViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album: Album = albumDataSet[position]
        holder.bindAlbum(album)
    }

    override fun getItemCount(): Int {
        return albumDataSet.size
    }

}