package com.jackpickus.myplaymusic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jackpickus.myplaymusic.R
import com.jackpickus.myplaymusic.activities.MusicActivity
import com.jackpickus.myplaymusic.models.Music

private const val ARG_ARTIST_ID:String = "music_artist"

class ArtistInfoFragment : Fragment() {
    private lateinit var mArtistTextView: TextView
    private lateinit var mMusicArtist: String
    private lateinit var mSongRecyclerView: RecyclerView
    private lateinit var mAdapter: ArtistMusicAdapter
    private lateinit var songsByArtist: ArrayList<Music>

    companion object {
        fun newInstance(musicArtist: String) : ArtistInfoFragment {
            val args = Bundle()
            args.putSerializable(ARG_ARTIST_ID, musicArtist)

            val fragment = ArtistInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mMusicArtist = (arguments!!.getSerializable(ARG_ARTIST_ID) as String?).toString()

        songsByArtist = ArrayList()

        for (song in MusicListFragment.newMusics) {
            if (song.artist == mMusicArtist) {
                songsByArtist.add(song)
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_about_artist, container, false)
        mArtistTextView = view.findViewById(R.id.artist_title_text_view)
        mArtistTextView.text = mMusicArtist

        mSongRecyclerView = view.findViewById(R.id.artists_music_recycler_view)
        mSongRecyclerView.layoutManager = LinearLayoutManager(activity)

        mAdapter = ArtistMusicAdapter(songsByArtist) { music -> songClicked(music) }
        mSongRecyclerView.adapter = mAdapter

        return view
    }

    private fun songClicked(song: Music) {
        val intent = MusicActivity.newIntent(this.activity, song.id)
        startActivity(intent)
    }

    class ArtistMusicAdapter(private val musicDataSet: ArrayList<Music>, private val onClick: (Music) -> Unit) :
            RecyclerView.Adapter<ArtistMusicAdapter.ArtistSongViewHolder>() {

        class ArtistSongViewHolder(view: View, val onClick: (Music) -> Unit) : RecyclerView.ViewHolder(view) {
            private val mTitleTextView: TextView
            private val mAlbumTextView: TextView
            private var currentSong: Music? = null

            init {
                // Define click listener for the ViewHolder's View.
                view.setOnClickListener{
                    currentSong?.let {
                        onClick(it)
                    }
                }

                mTitleTextView = view.findViewById(R.id.list_item_primary_text_view)

                // am reusing the list item layout and the second textview is named for artist
                // even though we are displaying the album
                mAlbumTextView = view.findViewById(R.id.list_item_secondary_text_view)
            }

            fun bindMusic(song: Music) {
                currentSong = song
                mTitleTextView.text = song.title
                mAlbumTextView.text = song.album
            }

        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ArtistSongViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.list_item_music, viewGroup, false)

            return ArtistSongViewHolder(view, onClick)
        }

        override fun onBindViewHolder(viewHolder: ArtistSongViewHolder, position: Int) {
            val music:Music = musicDataSet[position]
            viewHolder.bindMusic(music)
        }

        override fun getItemCount() = musicDataSet.size

    }

}