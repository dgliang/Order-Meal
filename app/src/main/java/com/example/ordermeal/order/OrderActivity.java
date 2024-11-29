package com.example.ordermeal.order;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.example.ordermeal.R;
import com.example.ordermeal.utils.FunctionHelper;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class OrderActivity extends AppCompatActivity {
    // 声明常量和变量，用于保存套餐的价格、数量、计算后的价格等信息
    public static final String DATA_TITLE = "TITLE";
    String title;

    // 每个套餐的单价
    double meal1 = 10.5, meal2 = 34.0, meal3 = 23.7, meal4 = 22.5, meal5 = 16.5, meal6 = 26.0;

    // 各套餐的数量，123总数量
    int itemCount1 = 0, itemCount2 = 0, itemCount3 = 0, itemCount4 = 0, itemCount5 = 0, itemCount6 = 0, totalItems;

    // 每个套餐的总价和总价
    double count1, count2, count3, count4, count5, count6, totalPrice;

    // 增加和减少按钮
    ImageView imageAdd1, imageAdd2, imageAdd3, imageAdd4, imageAdd5, imageAdd6,
            imageMinus1, imageMinus2, imageMinus3, imageMinus4, imageMinus5, imageMinus6;
    Toolbar toolbar;

    // 各套餐的TextView
    TextView tvMeal1, tvMeal2, tvMeal3, tvMeal4, tvMeal5, tvMeal6, tvMeal11, tvAmount, tvTotalPrice;

    // 结账按钮
    MaterialButton btnCheckout;
    OrderViewModel orderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        initStateBar();
        initLayout();
        initMeal1();
        initMeal2();
        initMeal3();
        initMeal4();
        initMeal5();
        initMeal6();
        initInputData();
    }

    // 初始化UI布局，绑定视图组件
    private void initLayout() {
        tvMeal11 = findViewById(R.id.tvMeal11);
        toolbar = findViewById(R.id.toolbar);
        tvMeal1 = findViewById(R.id.tvMeal1);
        tvMeal2 = findViewById(R.id.tvMeal2);
        tvMeal3 = findViewById(R.id.tvMeal3);
        tvMeal4 = findViewById(R.id.tvMeal4);
        tvMeal5 = findViewById(R.id.tvMeal5);
        tvMeal6 = findViewById(R.id.tvMeal6);
        tvAmount = findViewById(R.id.tvOrderAmount);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        imageAdd1 = findViewById(R.id.imageAdd1);
        imageAdd2 = findViewById(R.id.imageAdd2);
        imageAdd3 = findViewById(R.id.imageAdd3);
        imageAdd4 = findViewById(R.id.imageAdd4);
        imageAdd5 = findViewById(R.id.imageAdd5);
        imageAdd6 = findViewById(R.id.imageAdd6);
        imageMinus1 = findViewById(R.id.imageMinus1);
        imageMinus2 = findViewById(R.id.imageMinus2);
        imageMinus3 = findViewById(R.id.imageMinus3);
        imageMinus4 = findViewById(R.id.imageMinus4);
        imageMinus5 = findViewById(R.id.imageMinus5);
        imageMinus6 = findViewById(R.id.imageMinus6);
        btnCheckout = findViewById(R.id.btnCheckout);

        // 获取传递过来的标题，并设置到工具栏
        title = Objects.requireNonNull(getIntent().getExtras()).getString(DATA_TITLE);
        if (title != null) {

            setSupportActionBar(toolbar);
            assert getSupportActionBar() != null;
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(title);
        }

        // 为套餐 1 的 TextView 添加删除线效果
        tvMeal11.setPaintFlags(tvMeal11.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
    }

    // 初始化套餐 1 的数量增加/减少功能
    private void initMeal1() {
        imageAdd1.setOnClickListener(view -> {
            itemCount1 = itemCount1 + 1;
            tvMeal1.setText(String.valueOf(itemCount1));
            count1 = meal1 * itemCount1;
            initTotalPrice();
        });

        imageMinus1.setOnClickListener(view -> {
            if (itemCount1 > 0) {
                itemCount1 = itemCount1 - 1;
                tvMeal1.setText(String.valueOf(itemCount1));
            }
            count1 = meal1 * itemCount1;
            initTotalPrice();
        });
    }

    // 初始化套餐 2 的数量增加/减少功能
    private void initMeal2() {
        imageAdd2.setOnClickListener(view -> {
            itemCount2 = itemCount2 + 1;
            tvMeal2.setText(String.valueOf(itemCount2));
            count2 = meal2 * itemCount2;
            initTotalPrice();
        });

        imageMinus2.setOnClickListener(view -> {
            if (itemCount2 > 0) {
                itemCount2 = itemCount2 - 1;
                tvMeal2.setText(String.valueOf(itemCount2));
            }
            count2 = meal2 * itemCount2;
            initTotalPrice();
        });
    }

    // 初始化套餐 3 的数量增加/减少功能
    private void initMeal3() {
        imageAdd3.setOnClickListener(view -> {
            itemCount3 = itemCount3 + 1;
            tvMeal3.setText(String.valueOf(itemCount3));
            count3 = meal3 * itemCount3;
            initTotalPrice();
        });

        imageMinus3.setOnClickListener(view -> {
            if (itemCount3 > 0) {
                itemCount3 = itemCount3 - 1;
                tvMeal3.setText(String.valueOf(itemCount3));
            }
            count3 = meal3 * itemCount3;
            initTotalPrice();
        });
    }

    // 初始化套餐 4 的数量增加/减少功能
    private void initMeal4() {
        imageAdd4.setOnClickListener(view -> {
            itemCount4 = itemCount4 + 1;
            tvMeal4.setText(String.valueOf(itemCount4));
            count4 = meal4 * itemCount4;
            initTotalPrice();
        });

        imageMinus4.setOnClickListener(view -> {
            if (itemCount4 > 0) {
                itemCount4 = itemCount4 - 1;
                tvMeal4.setText(String.valueOf(itemCount4));
            }
            count4 = meal4 * itemCount4;
            initTotalPrice();
        });
    }

    // 初始化套餐 5 的数量增加/减少功能
    private void initMeal5() {
        imageAdd5.setOnClickListener(view -> {
            itemCount5 = itemCount1 + 1;
            tvMeal5.setText(String.valueOf(itemCount5));
            count5 = meal5 * itemCount5;
            initTotalPrice();
        });

        imageMinus5.setOnClickListener(view -> {
            if (itemCount5 > 0) {
                itemCount5 = itemCount5 - 1;
                tvMeal5.setText(String.valueOf(itemCount5));
            }
            count5 = meal5 * itemCount5;
            initTotalPrice();
        });
    }

    // 初始化套餐 6 的数量增加/减少功能
    private void initMeal6() {
        imageAdd6.setOnClickListener(view -> {
            itemCount6 = itemCount6 + 1;
            tvMeal6.setText(String.valueOf(itemCount6));
            count6 = meal6 * itemCount6;
            initTotalPrice();
        });

        imageMinus6.setOnClickListener(view -> {
            if (itemCount6 > 0) {
                itemCount6 = itemCount6 - 1;
                tvMeal6.setText(String.valueOf(itemCount6));
            }
            count6 = meal6 * itemCount6;
            initTotalPrice();
        });
    }

    // 更新总价格和总数量
    @SuppressLint("SetTextI18n")
    private void initTotalPrice() {
        totalItems = itemCount1 + itemCount2 + itemCount3 + itemCount4 + itemCount5 + itemCount6;
        totalPrice = count1 + count2 + count3 + count4 + count5 + count6;

        // 显示总数量到 tvAmount
        tvAmount.setText(totalItems + " 份");
        tvTotalPrice.setText(FunctionHelper.priceFormat(totalPrice));
    }

    // 初始化结账按钮的点击事件
    private void initInputData() {
        btnCheckout.setOnClickListener(view -> {
            // 检查用户是否选择了套餐，如果没有选择任何套餐，弹出提示
            if (totalItems == 0 || totalPrice == 0) {
                Toast.makeText(OrderActivity.this, "请先选择食物菜单！", Toast.LENGTH_SHORT).show();
            }
//            // 检查用户选择的套餐数量是否符合最小订单要求（至少10个）
//            else if (totalItems < 10) {
//                Toast.makeText(OrderActivity.this, "最少需要 10 份订单！", Toast.LENGTH_SHORT).show();
//            }
            // 如果选择的套餐符合条件，添加订单并显示成功提示
            else {
                orderViewModel.addDataOrder(title, totalItems, totalPrice);
                Toast.makeText(OrderActivity.this, "您的订单正在处理中，请前往历史菜单中查看！", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    // 初始化状态栏样式
    public void initStateBar() {
        if (Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    // 设置窗口标志，控制状态栏等界面元素的样式
    public static void setWindowFlag(@NonNull Activity activity, final int bits, boolean on) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();

        if (on) {
            // 开启对应标志
            layoutParams.flags |= bits;
        } else {
            // 关闭对应标志
            layoutParams.flags &= ~bits;
        }

        window.setAttributes(layoutParams);
    }

    // 处理工具栏的返回按钮点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}