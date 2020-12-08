package com.jackpickus.myplaymusic;

import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MusicHolder extends RecyclerView.ViewHolder {

    public TextureView mTitleTextView;

    public MusicHolder(@NonNull View itemView) {
        super(itemView);

//        mTitleTextView = (TextView) itemView;
    }
}
