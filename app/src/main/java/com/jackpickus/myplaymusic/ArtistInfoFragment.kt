package com.jackpickus.myplaymusic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jackpickus.myplaymusic.models.Music

const val ARG_ARTIST_ID:String = "music_artist"

class ArtistInfoFragment : Fragment() {

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

        val musicArtist = activity?.intent?.getSerializableExtra(ARG_ARTIST_ID) as String
        val songsByArtist = ArrayList<Music>()

        for (song in MusicListFragment.newMusics) {
            if (song.artist == musicArtist) {
                songsByArtist.add(song)
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}