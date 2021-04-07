package com.example.keepaccount;

import android.app.Application;

import com.example.keepaccount.database.DBManager;

public class UniteAPP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//初始化数据库
        DBManager.initDB(getApplicationContext());
    }
}
