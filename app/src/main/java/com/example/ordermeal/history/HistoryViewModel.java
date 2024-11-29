package com.example.ordermeal.history;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ordermeal.database.DatabaseClient;
import com.example.ordermeal.database.DatabaseModel;
import com.example.ordermeal.database.dao.DBDao;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HistoryViewModel extends AndroidViewModel {
    LiveData<List<DatabaseModel>> modelDatabase; // LiveData对象，用于观察历史记录数据的变化
    DBDao dbDao;

    public HistoryViewModel(@NonNull Application application) {
        super(application);

        dbDao = DatabaseClient.getInstance(application).getAppDatabase().dbDao();

        modelDatabase = dbDao.getAllOrder();
    }

    // 获取历史记录数据的LiveData对象
    public LiveData<List<DatabaseModel>> getDataList() {
        return modelDatabase;
    }

    // 删除指定UID的数据项
    public void deleteDataById(final int uid) {
        Completable.fromAction(() -> dbDao.deleteSingleData(uid))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
