package com.example.keepaccount.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 负责管理数据库的类
 * 主要对于表当中的内容进行操作，增删改查
 */

public class DBManager {

    private static SQLiteDatabase db;

    //初始化数据库对象
    public static void initDB(Context context) {
        DBOpenHelper helper = new DBOpenHelper(context);//得到帮助类对象
        db = helper.getWritableDatabase(); //得到数据库对象
    }

    /**
     * 读取数据库当中的数据，写入内存集合里
     * kind :表示收入或者支出
     */
    public static List<TypeBean> getTypeList(int kind) {
        List<TypeBean> list = new ArrayList<>();
        //读取typetb表当中的数据
        String sql = "select * from typetb where kind = " + kind;
        Cursor cursor = db.rawQuery(sql, null);
//        循环读取游标内容，存储到对象当中
        while (cursor.moveToNext()) {
            String typename = cursor.getString(cursor.getColumnIndex("typename"));
            int imageId = cursor.getInt(cursor.getColumnIndex("imageId"));
            int sImageId = cursor.getInt(cursor.getColumnIndex("sImageId"));
            int kind1 = cursor.getInt(cursor.getColumnIndex("kind"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            TypeBean typeBean = new TypeBean(id, typename, imageId, sImageId, kind);
            list.add(typeBean);
        }
        return list;
    }


    /*
     * 向记账表当中插入一条元素
     * */
    public static void insertItemToAccounttb(AccountBean bean) {
        ContentValues values = new ContentValues();
        values.put("typename", bean.getTypename());
        values.put("imageId", bean.getImageId());
        values.put("Remark", bean.getRemark());
        values.put("money", bean.getMoney());
        values.put("time", bean.getTime());
        values.put("year", bean.getYear());
        values.put("month", bean.getMonth());
        values.put("day", bean.getDay());
        values.put("kind", bean.getKind());
        db.insert("accounttb", null, values);
    }


    /**
     * 获取某一月的支出或者收入的总金额   kind：支出==0    收入===1
     * */
    public static float getSumMoneyOneMonth(int year,int month,int kind){
        float total = 0.0f;
        String sql = "select sum(money) from accounttb where year=? and month=? and kind=?";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", kind + ""});
        // 遍历
        if (cursor.moveToFirst()) {
            float money = cursor.getFloat(cursor.getColumnIndex("sum(money)"));
            total = money;
        }
        return total;
    }

    /*
     * 获取记账表当中某一天的所有支出或者收入情况
     * */


    public static List<AccountBean> getAccountListDayFromAccounttb(int year, int month, int day) {
        List<AccountBean> list = new ArrayList<>();
        String sql = "select * from accounttb where year=? and month=? and day=? order by id desc";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", day + ""});
        //遍历符合要求的每一行数据
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String typename = cursor.getString(cursor.getColumnIndex("typename"));
            String remark = cursor.getString(cursor.getColumnIndex("remark"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            int imageId = cursor.getInt(cursor.getColumnIndex("imageId"));
            int kind = cursor.getInt(cursor.getColumnIndex("kind"));
            float money = cursor.getFloat(cursor.getColumnIndex("money"));
            AccountBean accountBean = new AccountBean(id, typename, imageId, remark, money, time, year, month, day, kind);
            list.add(accountBean);
        }
        return list;
    }
}

















