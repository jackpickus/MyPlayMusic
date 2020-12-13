package com.jackpickus.myplaymusic;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MusicListFragment extends Fragment {

    private RecyclerView mMusicRecyclerView;
    private MusicAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_list, container, false);

        mMusicRecyclerView = view.findViewById(R.id.music_recycler_view);
        mMusicRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        MusicLab musicLab = MusicLab.get(getActivity());
        List<Music> musics = musicLab.getMusics();

        mAdapter = new MusicAdapter(musics);
        mMusicRecyclerView.setAdapter(mAdapter);
    }



    private class MusicHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Music mMusic;

        private TextView mTitleTextView;
        private TextView mArtistTextView;

        public MusicHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = itemView.findViewById(R.id.list_item_music_title_text_view);
            mArtistTextView = itemView.findViewById(R.id.list_item_music_artist_text_view);
        }

        public void bindMusic(Music music) {
            mMusic = music;
            mTitleTextView.setText(mMusic.getTitle());
            mArtistTextView.setText(mMusic.getArtist());
        }

        @Override
        public void onClick(View v) {
//            Intent intent = MusicActivity.newIntent(getActivity(), mMusic.getId());
            Intent intent = MusicPagerActivity.newIntent(getActivity(), mMusic.getId());
            startActivity(intent);
        }

    }

    private class MusicAdapter extends RecyclerView.Adapter<MusicHolder> {

        private List<Music> mMusics;

        public MusicAdapter(List<Music> musics) {
            mMusics = musics;
        }

        @NonNull
        @Override
        public MusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_music, parent, false);
            return new MusicHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MusicHolder holder, int position) {
            Music music = mMusics.get(position);
            holder.bindMusic(music);
        }

        @Override
        public int getItemCount() {
            return mMusics.size();
        }
    }

}
