package com.example.ordermeal.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordermeal.R;

import java.util.List;

// 定义一个适配器类，用于将流行菜单（ModelTrending）的数据绑定到 RecyclerView 的每个项
public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.ViewHolder> {
    List<ModelTrending> modelTrendingList; // 存储流行菜单数据的列表
    Context context;

    public TrendingAdapter(Context context, List<ModelTrending> items) {
        this.context = context;
        this.modelTrendingList = items;
    }

    // 创建新的视图项（ViewHolder），每个项的布局由 list_item_trending.xml 提供
    @NonNull
    @Override
    public TrendingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_trending, parent, false);
        return new TrendingAdapter.ViewHolder(view);
    }

    // 将数据绑定到对应的视图项
    @Override
    public void onBindViewHolder(TrendingAdapter.ViewHolder holder, int position) {
        final ModelTrending data = modelTrendingList.get(position);

        holder.imgThumb.setImageResource(data.getImgThumb()); // 设置图片资源
        holder.tvPlaceName.setText(data.getTvPlaceName()); // 设置菜单名称
        holder.tvVote.setText(data.getTvVote()); // 设置投票信息
    }

    @Override
    public int getItemCount() {
        return modelTrendingList.size();
    }

    // ViewHolder 用于缓存视图中的控件，提高 RecyclerView 的性能
    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvPlaceName, tvVote;
        public ImageView imgThumb;

        public ViewHolder(View itemView) {
            super(itemView);

            tvPlaceName = itemView.findViewById(R.id.tvPlaceName);
            tvVote = itemView.findViewById(R.id.tvVote);
            imgThumb = itemView.findViewById(R.id.imgThumb);
        }
    }

}
