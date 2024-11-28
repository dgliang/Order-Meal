package com.example.ordermeal.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private static DatabaseClient instance;
    AppDatabase appDB;

    // 数据库命名
    private DatabaseClient(Context context) {
        appDB = Room.databaseBuilder(context, AppDatabase.class, "order_meal_db_db")
                .fallbackToDestructiveMigration()
                .build();
    }

    // 实时同步数据
    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDB;
    }

}
