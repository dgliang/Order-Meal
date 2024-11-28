package com.example.ordermeal.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

// 为数据库创建表和列。表名 = 表；名称 = 列；
@Entity(tableName = "order_meal_db")
public class DatabaseModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "menu_name")
    public String menu_name;

    @ColumnInfo(name = "amount")
    public int amount;

    @ColumnInfo(name = "price")
    public double price;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "username")
    public String username;

    @ColumnInfo(name = "password")
    public String password;

    public DatabaseModel() {}

    public String getMenu_name() {
        return menu_name;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

}
