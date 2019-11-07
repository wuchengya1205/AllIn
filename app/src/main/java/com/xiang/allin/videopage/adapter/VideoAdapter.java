package com.xiang.allin.videopage.adapter;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiang.allin.R;
import com.xiang.allin.videopage.bean.VideoData;
import com.xiang.allin.videopage.myview.ListVideoView;
import com.xiang.allin.videopage.utils.VideoUtils;
import java.util.List;

/**
 * author : wuchengya
 * e-mail : wucy1205@yeah.net
 * date   : 2019/11/5
 * time   :15:07
 * desc   :ohuo
 * version: 1.0
 */
public class VideoAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private RecyclerView recyclerView;
    private List<VideoData> mockVideoData;
    setOnItemClickListener setOnItemClickListener;

    public VideoAdapter(Context mContext, RecyclerView recyclerView, List<VideoData> mockVideoData) {
        this.mContext = mContext;
        this.recyclerView = recyclerView;
        this.mockVideoData = mockVideoData;
    }


    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_item_video, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final VideoViewHolder holder1 = (VideoViewHolder) holder;
        holder1.sdvCover.setImageURI(mockVideoData.get(position).getCoverUrl());
        fitVideoScaleType(holder1,position);

    }

    private void fitVideoScaleType(VideoViewHolder holder, int position) {
        int contentWidth = recyclerView.getWidth();
        int contentHeight = recyclerView.getHeight();
        VideoUtils.ScaleType scaleType = VideoUtils.getImageCropType(new Pair<>(contentWidth, contentHeight),
                new Pair<>(mockVideoData.get(position).getWidth(), mockVideoData.get(position).getHeight()));
        if (scaleType == VideoUtils.ScaleType.CENTER_CROP) {
            holder.sdvCover.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
        } else if (scaleType == VideoUtils.ScaleType.FIT_CENTER) {
            holder.sdvCover.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
        }
    }

    @Override
    public int getItemCount() {
        return mockVideoData.size();
    }

    public VideoData getDataByPosition(int position) {
        return mockVideoData.get(position);
    }

   public class VideoViewHolder extends RecyclerView.ViewHolder {

        public ListVideoView videoView;
        public SimpleDraweeView sdvCover;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            sdvCover = itemView.findViewById(R.id.sdv_cover);
        }
    }

    public void itemClickListener(setOnItemClickListener setOnItemClickListener){
        this.setOnItemClickListener = setOnItemClickListener;
    }

    interface setOnItemClickListener{
        void onItemClickListener(int position ,VideoViewHolder holder);
    }
}
