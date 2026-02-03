package com.vincent.java;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vincent.java.databinding.ActivitySqliteBinding;

public class SqliteActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    private SQLiteDatabase db;

    ActivitySqliteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySqliteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(40, systemBars.top, 40, systemBars.bottom);
            return insets;
        });
        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getWritableDatabase();
    }

    public void addUser(View view) {
        String name = binding.nameEt.getText().toString();
        String phone = binding.phoneEt.getText().toString();
        if (name.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "姓名或电话不能为空",Toast.LENGTH_LONG).show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        db.insert("user", null, values);
        Toast.makeText(this, "用户添加成功！", Toast.LENGTH_LONG).show();
        binding.nameEt.setText("");
        binding.phoneEt.setText("");
    }
    @SuppressLint("Range")
    public void findAll(View view) {
        binding.contentTv.setText("");

        // 查询所有用户
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String phone = cursor.getString(cursor.getColumnIndex("phone"));
                binding.contentTv.append("姓名：" + name + "，电话：" + phone + "\n");
            } while (cursor.moveToNext());
        }
    }

    public void updateUser(View view) {
        // 根据姓名更新电话
        String name = binding.nameEt.getText().toString();
        String phone = binding.phoneEt.getText().toString();
        if (name.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "姓名或电话不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        ContentValues values = new ContentValues();
        values.put("phone", phone);
        db.update("user", values, "name=?", new String[]{name});
        Toast.makeText(this, "数据更新成功！", Toast.LENGTH_LONG).show();
        findAll(null);
    }

    public void deleteUser(View view) {
        // 根据姓名删除用户
        String name = binding.nameEt.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(this, "姓名不能为空", Toast.LENGTH_LONG).show();
            return;
        }

        // 查询用户是否存在
        Cursor cursor = db.query("user", null, "name=?", new String[]{name}, null, null, null);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "用户不存在", Toast.LENGTH_LONG).show();
            return;
        }
        db.delete("user", "name=?", new String[]{name});
        Toast.makeText(this, "删除成功！", Toast.LENGTH_LONG).show();
        findAll(null);
    }

}