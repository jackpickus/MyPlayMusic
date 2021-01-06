package com.jackpickus.myplaymusic

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment

const val EXTRA_ARTIST_INFO:String = "com.pickustechnologies.myplaymusic.artist_id"

class ArtistInfoActivity : SingleFragmentActivity(){

    override fun createFragment(): Fragment? {
        val musicArtist:String = intent.getSerializableExtra(EXTRA_ARTIST_INFO) as String
        return ArtistInfoFragment.newInstance(musicArtist)
    }

    companion object {
        @JvmStatic
        fun newIntent(packageContext: Context, musicArtist: String): Intent {
            val intent = Intent(packageContext, ArtistInfoActivity::class.java)
            intent.putExtra(EXTRA_ARTIST_INFO, musicArtist)
            return intent
        }
    }
}