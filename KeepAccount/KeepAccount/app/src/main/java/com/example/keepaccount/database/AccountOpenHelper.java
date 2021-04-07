package com.example.keepaccount.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AccountOpenHelper extends SQLiteOpenHelper {

    public AccountOpenHelper(Context context) {
        //它保存到文件里面，再从文件里面读取出来
        super(context, "account.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //第一次实例化对象的时候会调用
        sqLiteDatabase.execSQL("create table account(_id integer primary key autoincrement,sum int(100),remark varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //升级的时候会调用（例如：version从1变成2）
    }
}
