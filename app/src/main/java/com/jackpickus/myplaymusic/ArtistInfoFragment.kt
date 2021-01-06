package com.jackpickus.myplaymusic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.jackpickus.myplaymusic.models.Music

const val ARG_ARTIST_ID:String = "music_artist"

class ArtistInfoFragment : Fragment() {
    private lateinit var mArtistTextView: TextView
    private lateinit var mMusicArtist: String

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

        mMusicArtist = activity?.intent?.getSerializableExtra(ARG_ARTIST_ID) as String
        val songsByArtist = ArrayList<Music>()

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

        return view
    }

}