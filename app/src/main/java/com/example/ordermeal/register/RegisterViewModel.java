package com.example.ordermeal.register;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.ordermeal.database.DatabaseClient;
import com.example.ordermeal.database.DatabaseModel;
import com.example.ordermeal.database.dao.DBDao;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RegisterViewModel extends AndroidViewModel {
    DBDao dbDao; // 定义数据库数据访问对象，用于与数据库交互

    public RegisterViewModel(@NonNull Application application) {
        super(application);

        dbDao = DatabaseClient.getInstance(application).getAppDatabase().dbDao();
    }

    // 将用户注册数据保存到数据库
    public void addDataRegister(final String email, final String userName, final String password) {
        // 使用 RxJava 的 Completable 来处理数据库操作
        Completable.fromAction(() -> {
                    DatabaseModel databaseModel = new DatabaseModel();
                    databaseModel.email = email;
                    databaseModel.username = userName;
                    databaseModel.password = password;

                    // 将创建的注册数据插入到数据库中
                    dbDao.insertData(databaseModel);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

}
