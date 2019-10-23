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
    public void onBindViewHolder(@NonNull RecyclerListHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
        holder.content.setText(data.get(position).getAuthor_name());
        Glide.with(context).load(data.get(position).getThumbnail_pic_s()).into(holder.image);
        holder.image.setScaleType(ImageView.ScaleType.CENTER);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class RecyclerListHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public  TextView content;
        public  ImageView image;

        public RecyclerListHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            image = itemView.findViewById(R.id.image);
        }
    }
}
