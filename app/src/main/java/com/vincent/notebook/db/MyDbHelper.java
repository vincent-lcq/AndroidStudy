package com.vincent.notebook.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.vincent.notebook.bean.Notepad;

import java.util.ArrayList;
import java.util.List;

// 数据库帮助类
public class MyDbHelper extends SQLiteOpenHelper {

    private static final int version = 1;
    private static final String name = "notepad.db";

    public MyDbHelper(@Nullable Context context) {
        super(context, name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table notepad(id integer primary key autoincrement,content text,time text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // 新增记录 insert
    @SuppressLint("Range")
    public void insert(Notepad notepad) {
        String sql = "insert into notepad(content, time) values(?,?)";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql, new String[]{notepad.getContent(), notepad.getTime()});
    }

    // 查询所有记录
    public List<Notepad> findAll() {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from notepad";
        Cursor cursor = db.rawQuery(sql, null);
        List<Notepad> notepadList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Notepad notepad = new Notepad();
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String time = cursor.getString(cursor.getColumnIndex("time"));

            notepad.setId(id);
            notepad.setContent(content);
            notepad.setTime(time);

            notepadList.add(notepad);
        }

        cursor.close();
        return notepadList;
    }
}
