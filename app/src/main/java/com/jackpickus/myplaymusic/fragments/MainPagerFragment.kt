package com.jackpickus.myplaymusic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jackpickus.myplaymusic.R
import com.jackpickus.myplaymusic.adapters.MusicCollectionAdapter

class MainPagerFragment : Fragment() {
    private lateinit var musicCollectionAdapter: MusicCollectionAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_main_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        musicCollectionAdapter = MusicCollectionAdapter(this)
        viewPager = view.findViewById(R.id.fragment_main_pager)
        viewPager.adapter = musicCollectionAdapter

        val tabLayout = view.findViewById(R.id.tab_layout) as TabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = "FAVORITES"
                1 -> tab.text = "PLAYLISTS"
                2 -> tab.text = "ARTISTS"
                3 -> tab.text = "ALBUMS"
                else -> tab.text = "SONGS"
            }
        }.attach()
        viewPager.currentItem = musicCollectionAdapter.getItemId(2).toInt()

    }
}
