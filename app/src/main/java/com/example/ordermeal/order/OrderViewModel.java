package com.example.ordermeal.order;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.ordermeal.database.DatabaseClient;
import com.example.ordermeal.database.DatabaseModel;
import com.example.ordermeal.database.dao.DBDao;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;


// OrderViewModel 类负责处理订单数据的业务逻辑，并通过 RxJava 与数据库进行交互。
public class OrderViewModel extends AndroidViewModel {

    DBDao dbDao;

    public OrderViewModel(@NonNull Application application) {
        super(application);

        dbDao = DatabaseClient.getInstance(application).getAppDatabase().dbDao();
    }

    public void addDataOrder(final String strMenu, final int amount, final double price) {
        // 使用 RxJava 的 Completable 来执行数据库插入操作
        Completable.fromAction(() -> {
                    DatabaseModel databaseModel = new DatabaseModel();
                    databaseModel.menu_name = strMenu;
                    databaseModel.amount = amount;
                    databaseModel.price = price;

                    // 将数据插入数据库
                    dbDao.insertData(databaseModel);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
