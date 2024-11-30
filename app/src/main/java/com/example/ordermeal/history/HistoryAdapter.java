package com.example.ordermeal.history;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordermeal.R;
import com.example.ordermeal.database.DatabaseModel;
import com.example.ordermeal.utils.FunctionHelper;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    List<DatabaseModel> modelDatabase; // 存储数据库模型数据的列表，用于显示历史记录
    Context context;

    public HistoryAdapter(Context context, List<DatabaseModel> modelInputList) {
        this.context = context;
        this.modelDatabase = modelInputList;
    }

    // 用于更新适配器中的数据，清空原有数据并添加新的数据项
    @SuppressLint("NotifyDataSetChanged")
    public void initDataAdapter(List<DatabaseModel> items) {
        modelDatabase.clear();
        modelDatabase.addAll(items);
        notifyDataSetChanged();
    }

    // 创建视图持有者，用于加载单个列表项的布局
    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_history, parent, false);
        return new ViewHolder(view);
    }

    // 将数据项绑定到每个视图持有者上，更新UI
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(HistoryAdapter.ViewHolder holder, int position) {
        final DatabaseModel data = modelDatabase.get(position);

        holder.tvName.setText(data.getMenu_name());  // 设置菜单名称
        holder.tvDate.setText(FunctionHelper.getTodayTime());  // 设置当前日期
        holder.tvAmount.setText(data.getAmount() + " 份");  // 设置商品数量
        holder.tvPrice.setText(FunctionHelper.priceFormat(data.getPrice()));  // 格式化并显示价格
    }

    @Override
    public int getItemCount() {
        return modelDatabase.size();
    }

    // 视图持有者类，用于缓存每个列表项的视图组件
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvDate, tvAmount, tvPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvMealName);  // 菜单名称
            tvDate = itemView.findViewById(R.id.tvDate);  // 日期
            tvAmount = itemView.findViewById(R.id.tvAmount);  // 商品数量
            tvPrice = itemView.findViewById(R.id.tvPrice);  // 价格
        }
    }

    // 删除指定位置的项目，通常用于实现滑动删除功能
    public void initSwipeRemove(int position) {
        modelDatabase.remove(position);
        notifyItemRemoved(position);
    }

    // 恢复已删除的项目，重新插入到指定位置
    public void restoreItem(DatabaseModel databaseModel, int position) {
        modelDatabase.add(position, databaseModel);
        notifyItemInserted(position);
    }

    public List<DatabaseModel> getData() {
        return modelDatabase;
    }
}
