package com.vincent.java;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class DataStorageDemo extends AppCompatActivity {

    private Button btn1;
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data_storage_demo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
    }

    public void innerWrite(View view) throws Exception {
        FileOutputStream fos = openFileOutput("inner.txt", MODE_PRIVATE);
        fos.write("Hello, Inner Storage".getBytes());
        fos.close();
        Toast.makeText(this, "Inner Saved!", Toast.LENGTH_LONG).show();
    }
    
    public void innerRead(View view)throws Exception {
        FileInputStream fis = openFileInput("inner.txt");
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        Toast.makeText(this, new String(buffer), Toast.LENGTH_LONG).show();
    }

    public void outterWrite(View view) throws Exception{
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 写入数据
            File path = Environment.getExternalStorageDirectory();
            File file = new File(path, "outer.txt");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write("Hellout, outter sotrage".getBytes());
            fos.close();
            Toast.makeText(this, "Succeed!", Toast.LENGTH_LONG).show();
        }
    }

    public void outterRead(View view) throws Exception {
        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)) {
            File path = Environment.getExternalStorageDirectory();
            File file = new File(path, "outer.txt");
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            Toast.makeText(this, new String(buffer), Toast.LENGTH_LONG).show();
        }
    }

    public void spWrite(View view) {
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        sp.edit().putString("name", "Tom").apply();
        sp.edit().putInt("age", 38).apply();
        Toast.makeText(this, "写入成功", Toast.LENGTH_LONG).show();
    }

    public void spRead(View view) {
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        int age = sp.getInt("age", 0);
        String name = sp.getString("name", "");
        Toast.makeText(this, "name is " + name + ", age is " + age, Toast.LENGTH_LONG).show();
    }
}