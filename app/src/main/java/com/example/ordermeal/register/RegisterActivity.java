package com.example.ordermeal.register;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.ordermeal.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    // 定义视图控件，用于获取用户输入的数据
    TextInputEditText inputEmail;
    TextInputEditText inputUserName;
    TextInputEditText inputPassword;
    MaterialButton btnRegister;

    // 定义变量来保存用户输入的数据
    String email;
    String userName;
    String password;

    RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initLayout();
        initInputData();
    }

    // 初始化布局控件
    private void initLayout() {
        inputEmail = findViewById(R.id.inputEmail);
        inputUserName = findViewById(R.id.inputUser);
        inputPassword = findViewById(R.id.inputPassword);
        btnRegister = findViewById(R.id.btnRegister);

        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
    }

    // 初始化输入数据，并设置注册按钮的点击事件
    private void initInputData() {
        // 设置注册按钮的点击事件监听器
        btnRegister.setOnClickListener(view -> {
            email = Objects.requireNonNull(inputEmail.getText()).toString();
            userName = Objects.requireNonNull(inputUserName.getText()).toString();
            password = Objects.requireNonNull(inputPassword.getText()).toString();

            // 检查用户输入的字段是否为空
            if (email.isEmpty() || userName.isEmpty() || password.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "所有表格都必须填写！", Toast.LENGTH_SHORT).show();
            } else {
                registerViewModel.addDataRegister(email, userName, password);

                // 注册成功后，显示成功提示，并关闭当前页面
                Toast.makeText(RegisterActivity.this, "注册成功！请登录", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
