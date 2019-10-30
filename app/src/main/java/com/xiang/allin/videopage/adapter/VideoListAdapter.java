package com.xiang.allin.videopage.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xiang.allin.R;

import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * author : wuchengya
 * e-mail : wucy1205@yeah.net
 * date   : 2019/10/28
 * time   :18:05
 * desc   :ohuo
 * version: 1.0
 */
public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoHolder> {
    private Context context;
    private List<String> videoLists;

    public VideoListAdapter(Context context, List<String> videoLists) {
        this.context = context;
        this.videoLists = videoLists;
    }

    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoHolder(LayoutInflater.from(context).inflate(R.layout.video_list_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
            holder.jzplayer.setUp(videoLists.get(position),JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
            Glide.with(context).load("https://p3.pstatp.com/large/c0b300014a9ef7257e51.jpg").into(holder.jzplayer.thumbImageView);
    }

    @Override
    public int getItemCount() {
        return videoLists.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class VideoHolder extends RecyclerView.ViewHolder {

        public JZVideoPlayerStandard jzplayer;

        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            jzplayer = itemView.findViewById(R.id.jzplayer);
        }
    }
}
