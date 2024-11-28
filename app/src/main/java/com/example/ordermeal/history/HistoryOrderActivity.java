package com.example.ordermeal.history;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ordermeal.R;
import com.example.ordermeal.database.DatabaseModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryOrderActivity extends AppCompatActivity {
    List<DatabaseModel> modelDatabaseList = new ArrayList<>(); // 用于存储数据库中的历史记录
    HistoryAdapter historyAdapter; // 适配器，用于绑定数据到 RecyclerView
    HistoryViewModel historyViewModel; // 视图模型，用于与数据交互
    Toolbar toolbar; // Toolbar，显示在活动顶部的工具栏
    RecyclerView rvHistory; // RecyclerView，用于显示历史订单的列表
    TextView tvNotFound; // TextView，用于显示"未找到历史记录"的信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_order);

        initToolbar();

        initLayout();

        initViewModel();

        initSwipeToDelete();
    }

    // 初始化工具栏，设置返回按钮
    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    // 初始化布局，设置 RecyclerView 和 TextView
    private void initLayout() {
        rvHistory = findViewById(R.id.rvHistory);
        tvNotFound = findViewById(R.id.tvNotFound);

        // 初始状态下隐藏"未找到记录"的提示
        tvNotFound.setVisibility(View.GONE);

        historyAdapter = new HistoryAdapter(this, modelDatabaseList);
        rvHistory.setHasFixedSize(true);
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setAdapter(historyAdapter);
    }

    // 初始化视图模型，观察数据变化并更新 UI
    private void initViewModel() {
        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);

        historyViewModel.getDataList().observe(this, modelDatabases -> {
            // 如果数据不为空，更新适配器
            if (!modelDatabases.isEmpty()) {
                historyAdapter.initDataAdapter(modelDatabases);
            } else {
                // 如果数据为空，显示"未找到历史记录"提示
                tvNotFound.setVisibility(View.VISIBLE);
                rvHistory.setVisibility(View.GONE);  // 隐藏 RecyclerView
            }
        });
    }

    // 初始化滑动删除功能
    private void initSwipeToDelete() {
        ItemTouchHelper.SimpleCallback simpleCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START | ItemTouchHelper.END) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        final int position = viewHolder.getAdapterPosition();
                        final DatabaseModel databaseModel = historyAdapter.getData().get(position);

                        // 创建一个弹出框提示用户是否删除该项
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HistoryOrderActivity.this);
                        alertDialogBuilder.setMessage("是否删除这条历史记录？");
                        alertDialogBuilder.setPositiveButton("是的，删除", (dialogInterface, i) -> {
                            // 用户点击"删除"时，删除数据库中的该项数据
                            int uid = databaseModel.uid;
                            historyViewModel.deleteDataById(uid);
                            historyAdapter.initSwipeRemove(viewHolder.getAdapterPosition());
                            Toast.makeText(HistoryOrderActivity.this, "数据已删除", Toast.LENGTH_SHORT).show();
                        });

                        alertDialogBuilder.setNegativeButton("取消", (dialogInterface, i) -> {
                            // 用户点击"取消"时，恢复已删除的项
                            historyAdapter.restoreItem(databaseModel, position);
                            rvHistory.scrollToPosition(position);
                            initViewModel();
                            dialogInterface.cancel();
                        });

                        // 创建并显示提示对话框
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                };

        // 将回调绑定到 RecyclerView，实现滑动删除功能
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvHistory);
    }

    // 处理 Toolbar 中的菜单项点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
