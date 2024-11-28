package com.example.ordermeal.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.ordermeal.R;
import com.example.ordermeal.main.MainActivity;
import com.example.ordermeal.register.RegisterActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    // 按钮和输入框控件
    MaterialButton btnLogin;
    MaterialButton btnRegister;
    TextInputEditText inputUser;
    TextInputEditText inputPassword;

    // ViewModel 实例，用于与数据库进行交互
    LoginViewModel loginViewModel;

    String userName;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initLayout();

        initInputData();
    }

    // 初始化界面控件
    private void initLayout() {
        inputUser = findViewById(R.id.inputUser);
        inputPassword = findViewById(R.id.inputPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        loginViewModel = ViewModelProviders.of(LoginActivity.this).get(LoginViewModel.class);
    }

    // 初始化输入数据和按钮点击事件
    private void initInputData() {
        // 跳转到注册页面
        btnRegister.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        // 验证用户名和密码并进行登录
        btnLogin.setOnClickListener(view -> {
            userName = Objects.requireNonNull(inputUser.getText()).toString();
            password = Objects.requireNonNull(inputPassword.getText()).toString();

            loginViewModel.getDataUser(userName, password);

            // 如果用户名或密码为空，显示提示信息
            if (userName.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "所有表格都必须填写！", Toast.LENGTH_LONG).show();
            } else {
                // 如果用户名和密码都不为空，继续验证用户信息
                loginViewModel.getDataUser(userName, password).observe(LoginActivity.this,
                        modelDatabases -> {
                            if (!modelDatabases.isEmpty()) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                // 如果未查询到用户数据，提示用户名或密码错误
                                Toast.makeText(LoginActivity.this, "您的用户名或密码错误！", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }
}
