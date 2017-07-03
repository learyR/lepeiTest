package com.example.lr.test.app;

import android.app.Application;

import com.example.lr.test.model.db.DaoMaster;
import com.example.lr.test.model.db.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Lr on 2017/6/30.
 */

public class MyTestApplication extends Application {
    private DaoMaster.DevOpenHelper mHelper;
    private Database db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public static MyTestApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        createConversitionDB();
        createUserDB();
    }

    public static MyTestApplication getInstance() {
        if (instance == null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new MyTestApplication();
                }
            }
        }
        return instance;
    }

    private void createUserDB() {
        mHelper = new DaoMaster.DevOpenHelper(this, "User.db");
        db = mHelper.getWritableDb();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }


    private void createConversitionDB(){
        mHelper = new DaoMaster.DevOpenHelper(this, "Conversition.db");
        db = mHelper.getWritableDb();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public  DaoSession getDaoSession() {
        return mDaoSession;
    }
}
