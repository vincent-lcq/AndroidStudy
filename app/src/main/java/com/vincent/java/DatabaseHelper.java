package com.vincent.java;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "bitcat.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建表
        String sql = "create table user(id integer primary key autoincrement, name varchar(20), phone integer)";
        // 执行Sql语句
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
