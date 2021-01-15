package com.jackpickus.myplaymusic.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jackpickus.myplaymusic.fragments.*

class MusicCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> FavoritesFragment()
            1 -> PlaylistsFragment()
            2 -> ArtistsFragment()
            3 -> AlbumsFragment()
            else -> MusicListFragment()
        }
    }

}