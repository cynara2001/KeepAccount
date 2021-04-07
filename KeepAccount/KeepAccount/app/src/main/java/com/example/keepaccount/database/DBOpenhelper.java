package com.example.keepaccount.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.keepaccount.R;

public class DBOpenhelper extends SQLiteOpenHelper {
    public DBOpenhelper(@Nullable Context context) {
        super(context, "tally.db", null, 1);
    }

    int id;
    String typename;//类型名称
    int imageID;//被选中的图片的ID
    int sImageID;//未被选中的图片ID
    int kind;//收入-1 支出-0
//  创建数据库的方法,第一次运行时,会调用
    @Override
    public void onCreate(SQLiteDatabase db) {
       String sql = "create table typetb(id integer primary key autoincrement,typename varchar(10),imageId integer,sImageId integer,kind integer)";
       db.execSQL(sql);
       insertType(db);

    }

    private void insertType(SQLiteDatabase db) {
        //向typetb表中插入元素
        String sql = "insert into typetb(typename,imageId,sImageId,kind) values (?,?,?,?)";
        db.execSQL(sql,new Object[]{"其他", R.mipmap.other1,R.mipmap.other2,0});
        db.execSQL(sql,new Object[]{"正餐", R.mipmap.dinner1,R.mipmap.dinner2,0});
        db.execSQL(sql,new Object[]{"饮料零食", R.mipmap.drink1,R.mipmap.drink2,0});
        db.execSQL(sql,new Object[]{"水果", R.mipmap.fruits1,R.mipmap.fruits2,0});
        db.execSQL(sql,new Object[]{"日用品", R.mipmap.goods1,R.mipmap.goods2,0});
        db.execSQL(sql,new Object[]{"娱乐", R.mipmap.amusement1,R.mipmap.amusement2,0});
        db.execSQL(sql,new Object[]{"护肤化妆", R.mipmap.cosmetics1,R.mipmap.cosmetics2,0});
        db.execSQL(sql,new Object[]{"服饰", R.mipmap.costume1,R.mipmap.costume2,0});
        db.execSQL(sql,new Object[]{"数码", R.mipmap.digital1,R.mipmap.digital2,0});
        db.execSQL(sql,new Object[]{"交通", R.mipmap.traffic1,R.mipmap.traffic2,0});
        db.execSQL(sql,new Object[]{"通讯", R.mipmap.communicate1,R.mipmap.communicate2,0});
        db.execSQL(sql,new Object[]{"住房", R.mipmap.house1,R.mipmap.house2,0});
        db.execSQL(sql,new Object[]{"学习", R.mipmap.study1,R.mipmap.study2,0});
        db.execSQL(sql,new Object[]{"医疗", R.mipmap.treatment1,R.mipmap.treatment2,0});
        db.execSQL(sql,new Object[]{"礼物", R.mipmap.gift1,R.mipmap.gift2,0});

        db.execSQL(sql,new Object[]{"其他", R.mipmap.other1,R.mipmap.other2,1});
        db.execSQL(sql,new Object[]{"薪资", R.mipmap.salary1,R.mipmap.salary2,1});
        db.execSQL(sql,new Object[]{"奖金", R.mipmap.bonus1,R.mipmap.bonus2,1});
        db.execSQL(sql,new Object[]{"投资回报", R.mipmap.invest1,R.mipmap.invest2,1});
        db.execSQL(sql,new Object[]{"二手交易", R.mipmap.used1,R.mipmap.used2,1});

    }

    //  升级的时候会调用（例如：version从1变成2）
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
