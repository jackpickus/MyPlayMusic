package com.jackpickus.myplaymusic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MusicListFragment extends Fragment {

//    private RecyclerView mMusicRecyclerView;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_music_list, container, false);
//
//        mMusicRecyclerView = view.findViewById(R.id.music_recycler_view);
//        mMusicRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        return view;
//    }
//
//    private class MusicAdapter extends RecyclerView.Adapter<MusicHolder> {
//
//        private List<Music> mMusics;
//
//        public MusicAdapter(List<Music> musics) {
//            mMusics = musics;
//        }
//
//        @NonNull
//        @Override
//        public MusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
//            View view = layoutInflater
//                    .inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
//            return new MusicHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull MusicHolder holder, int position) {
//            Music music = new mMusics.get(position);
//            holder.mTitleTextView.setText(music.getTitle());
//        }
//
//        @Override
//        public int getItemCount() {
//            return 0;
//        }
//    }
}
