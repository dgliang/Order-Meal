package com.example.ordermeal.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordermeal.R;
import com.example.ordermeal.order.OrderActivity;

import java.util.List;

// 定义适配器类，将菜单分类数据绑定到 RecyclerView 的每个项
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    List<ModelCategories> modelCategoriesList; // 存储菜单分类数据的列表
    Context context;

    public CategoriesAdapter(Context context, List<ModelCategories> items) {
        this.context = context;
        this.modelCategoriesList = items;
    }

    // 创建新的视图项（ViewHolder），每个项的布局由 list_item_categories.xml 提供
    @NonNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_categories, parent, false);
        return new CategoriesAdapter.ViewHolder(view);
    }

    // 将数据绑定到对应的视图项
    @Override
    public void onBindViewHolder(CategoriesAdapter.ViewHolder holder, int position) {
        final ModelCategories data = modelCategoriesList.get(position);

        holder.imageIcon.setImageResource(data.getIcon()); // 设置图标资源
        holder.tvName.setText(data.getName()); // 设置分类名称

        // 为每个分类项设置点击事件，点击后跳转到 OrderActivity
        holder.cvCategories.setOnClickListener(view -> {
            Intent intent = new Intent(new Intent(context, OrderActivity.class));
            intent.putExtra(OrderActivity.DATA_TITLE, data.getName());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return modelCategoriesList.size();
    }

    // ViewHolder 用于缓存视图中的控件，提高 RecyclerView 的性能
    static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cvCategories;
        public TextView tvName;
        public ImageView imageIcon;

        public ViewHolder(View itemView) {
            super(itemView);

            cvCategories = itemView.findViewById(R.id.cvCategories);
            tvName = itemView.findViewById(R.id.tvName);
            imageIcon = itemView.findViewById(R.id.imageIcon);
        }
    }

}
