package com.xiang.allin.FirstPage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xiang.allin.R;
import com.xiang.allin.common.CommonBean;

import java.util.List;

/**
 * author : wuchengya
 * e-mail : wucy1205@yeah.net
 * date   : 2019/10/22
 * time   :16:23
 * desc   :ohuo
 * version: 1.0
 */
public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.RecyclerListHolder> {
    private Context context;
    private List<CommonBean.ResultBean.DataBean> data;
    private setOnItemClickListener setOnItemClickListener;

    public RecyclerListAdapter(Context context, List<CommonBean.ResultBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerListHolder(LayoutInflater.from(context).inflate(R.layout.recycler_list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerListHolder holder, final int position) {
        holder.title.setText(data.get(position).getTitle());
        holder.content.setText(data.get(position).getAuthor_name()+"     "+data.get(position).getDate());
        if (data.get(position).getThumbnail_pic_s() != null){
            holder.image1.setVisibility(View.VISIBLE);
            Glide.with(context).load(data.get(position).getThumbnail_pic_s()).into(holder.image1);
        }
        if (data.get(position).getThumbnail_pic_s02() != null){
            holder.image2.setVisibility(View.VISIBLE);
            Glide.with(context).load(data.get(position).getThumbnail_pic_s()).into(holder.image2);
        }else {
            holder.image2.setVisibility(View.GONE);
        }
        if (data.get(position).getThumbnail_pic_s03() != null){
            holder.image3.setVisibility(View.VISIBLE);
            Glide.with(context).load(data.get(position).getThumbnail_pic_s()).into(holder.image3);
        }else {
            holder.image3.setVisibility(View.GONE);
        }
        holder.image1.setScaleType(ImageView.ScaleType.CENTER);
        holder.image2.setScaleType(ImageView.ScaleType.CENTER);
        holder.image3.setScaleType(ImageView.ScaleType.CENTER);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnItemClickListener.onItemClickListener(position,holder,data.get(position).getUrl());
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RecyclerListHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public  TextView content;
        public  ImageView image1;
        public  ImageView image2;
        public  ImageView image3;

        public RecyclerListHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            image1 = itemView.findViewById(R.id.image1);
            image2 = itemView.findViewById(R.id.image2);
            image3 = itemView.findViewById(R.id.image3);
        }
    }
    public void setOnItem(setOnItemClickListener setOnItemClickListener){
        this.setOnItemClickListener = setOnItemClickListener;

    }

    public interface  setOnItemClickListener{
        void onItemClickListener(int position,RecyclerListHolder holder,String url);
    }
}
