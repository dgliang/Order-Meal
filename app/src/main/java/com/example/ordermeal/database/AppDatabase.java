package com.example.ordermeal.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ordermeal.database.dao.DBDao;

// 数据库模型抽象类
@Database(entities = {DatabaseModel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DBDao dbDao();
}
