package com.example.ordermeal.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordermeal.R;
import com.example.ordermeal.history.HistoryOrderActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // 存储分类和流行菜单的数据列表
    List<ModelCategories> modelCategoryList = new ArrayList<>();
    List<ModelTrending> modelTrendList = new ArrayList<>();

    // 适配器实例
    CategoriesAdapter categoryAdapter;
    TrendingAdapter trendAdapter;

    // 分类和流行菜单的单一数据模型
    ModelCategories modelCategory;
    ModelTrending modelTrend;

    // UI 控件：分类 RecyclerView 和流行菜单 RecyclerView
    RecyclerView rvCategory, rvTrend;

    // 历史订单按钮
    CardView cvHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initStateBar();
        initLayout();
        initCategories();
        initTrending();
    }

    // 初始化布局和 UI 组件
    private void initLayout() {
        cvHistory = findViewById(R.id.cvHistory);
        cvHistory.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, HistoryOrderActivity.class);
            startActivity(intent);
        });

        // 设置分类 RecyclerView 的布局管理器为网格布局，每行显示 3 个项
        rvCategory = findViewById(R.id.rvCategories);
        rvCategory.setLayoutManager(new GridLayoutManager(this, 3));
        rvCategory.setHasFixedSize(true);

        // 设置流行菜单 RecyclerView 的布局管理器为横向线性布局
        rvTrend = findViewById(R.id.rvTrending);
        rvTrend.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvTrend.setHasFixedSize(true);
    }

    // 初始化分类数据
    private void initCategories() {
        // 创建分类数据模型并添加到列表
        modelCategory = new ModelCategories(R.drawable.ic_complete, "Complete Package");
        modelCategoryList.add(modelCategory);
        modelCategory = new ModelCategories(R.drawable.ic_saving, "Saving Package");
        modelCategoryList.add(modelCategory);
        modelCategory = new ModelCategories(R.drawable.ic_healthy, "Healthy Package");
        modelCategoryList.add(modelCategory);
        modelCategory = new ModelCategories(R.drawable.ic_fast, "FastFood");
        modelCategoryList.add(modelCategory);
        modelCategory = new ModelCategories(R.drawable.ic_event, "Event Packages");
        modelCategoryList.add(modelCategory);
        modelCategory = new ModelCategories(R.drawable.ic_more_food, "Others");
        modelCategoryList.add(modelCategory);

        // 为 RecyclerView 设置适配器
        categoryAdapter = new CategoriesAdapter(this, modelCategoryList);
        rvCategory.setAdapter(categoryAdapter);
    }

    // 初始化流行菜单数据
    private void initTrending() {
        // 创建流行菜单数据模型并添加到列表
        modelTrend = new ModelTrending(R.drawable.complete_1,"Menu 1", "2,200 收藏");
        modelTrendList.add(modelTrend);
        modelTrend = new ModelTrending(R.drawable.complete_2,"Menu 2", "1,220 收藏");
        modelTrendList.add(modelTrend);
        modelTrend = new ModelTrending(R.drawable.complete_3,"Menu 3", "345 收藏");
        modelTrendList.add(modelTrend);
        modelTrend = new ModelTrending(R.drawable.complete_4,"Menu 4", "590 收藏");
        modelTrendList.add(modelTrend);

        // 为 RecyclerView 设置适配器
        trendAdapter = new TrendingAdapter(this, modelTrendList);
        rvTrend.setAdapter(trendAdapter);
    }

    // 设置状态栏样式，兼容不同的 Android 版本
    public void initStateBar() {
        if (Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    // 设置 Window 标志位，控制状态栏的透明度
    public static void setWindowFlag(@NonNull Activity activity, final int bits, boolean on) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        if (on) {
            // 设置标志位
            layoutParams.flags |= bits;
        } else {
            // 取消标志位
            layoutParams.flags &= ~bits;
        }

        // 应用新的标志位
        window.setAttributes(layoutParams);
    }

}