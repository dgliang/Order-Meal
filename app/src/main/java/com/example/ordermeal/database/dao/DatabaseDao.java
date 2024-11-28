package com.example.ordermeal.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ordermeal.database.DatabaseModel;

import java.util.List;

@Dao
public interface DatabaseDao {

    @Query("SELECT * FROM order_meal_db where uid != 1")
    LiveData<List<DatabaseModel>> getAllOrder();

    @Query("SELECT * FROM order_meal_db where username= :username AND password= :password")
    LiveData<List<DatabaseModel>> getUserByName(String username, String password);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(DatabaseModel... modelDatabases);

    @Query("UPDATE order_meal_db SET menu_name= :menu_name, amount= :amount, price= :price WHERE uid = :uid")
    void updateData(String menu_name, int amount, int price, int uid);

    @Query("DELETE FROM order_meal_db WHERE uid= :uid")
    void deleteSingleData(int uid);
}
