package com.example.lr.test.app;

import android.app.Application;

import com.example.lr.test.entity.DaoMaster;
import com.example.lr.test.entity.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Lr on 2017/6/30.
 */

public class MyTestApplication extends Application {
    private static MyTestApplication instance;
    private static DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "Conversition.db");
        Database database = helper.getWritableDb();
        DaoMaster master = new DaoMaster(database);
        daoSession = master.newSession();
    }
    public static MyTestApplication getInstance(){
        return instance;
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
