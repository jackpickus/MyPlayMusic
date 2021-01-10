package com.jackpickus.myplaymusic;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jackpickus.myplaymusic.models.Music;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MusicListFragment extends Fragment {

    private RecyclerView mMusicRecyclerView;
    private MusicAdapter mAdapter;
    private Context mContext;
    protected static List<Music> newMusics;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_list, container, false);

        mMusicRecyclerView = view.findViewById(R.id.music_recycler_view);
        mMusicRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // TODO update UI with songs after permissions are granted
        if (ContextCompat.checkSelfPermission(mContext.getApplicationContext(), WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            updateUI();
        }

        return view;
    }

    private void updateUI() {

        newMusics = getSongs();

        mAdapter = new MusicAdapter(newMusics);
        mMusicRecyclerView.setAdapter(mAdapter);
    }

    private List<Music> getSongs() {
        String[] projection = new String[]{
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME
        };

        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";

        List<Music> songs = new ArrayList<>();

        Cursor cursor = mContext.getApplicationContext().getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                sortOrder
        );

        while (cursor.moveToNext()) {
            String data = cursor.getString(4);
            Music m = new Music(data);
            m.setAlbum(cursor.getString(3));
            m.setTitle(cursor.getString(2));
            m.setArtist(cursor.getString(1));
            songs.add(m);
        }
        cursor.close();

        return songs;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    private class MusicHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Music mMusic;

        private TextView mTitleTextView;
        private TextView mArtistTextView;

        public MusicHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = itemView.findViewById(R.id.list_item_primary_text_view);
            mArtistTextView = itemView.findViewById(R.id.list_item_secondary_text_view);
        }

        public void bindMusic(Music music) {
            mMusic = music;
            mTitleTextView.setText(mMusic.getTitle());
            mArtistTextView.setText(mMusic.getArtist());
        }

        @Override
        public void onClick(View v) {
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
