package com.example.ordermeal.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ordermeal.database.DatabaseClient;
import com.example.ordermeal.database.DatabaseModel;
import com.example.ordermeal.database.dao.DatabaseDao;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {
    LiveData<List<DatabaseModel>> modelDatabase; // LiveData 用于观察数据库中查询到的用户数据
    DatabaseDao dbDao; // 数据库访问对象 (DAO)

    public LoginViewModel(@NonNull Application application) {
        super(application);

        dbDao = DatabaseClient.getInstance(application).getAppDatabase().databaseDao();
    }

    // 根据用户名和密码查询用户数据
    public LiveData<List<DatabaseModel>> getDataUser(String username, String password) {
        modelDatabase = dbDao.getUserByName(username, password);
        return modelDatabase;
    }
}
