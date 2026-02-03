package com.vincent.java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityC extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_c);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView contentTv = findViewById(R.id.textViewC);

//        Intent intent = getIntent();
//        String name = intent.getStringExtra("name");
//        int age = intent.getIntExtra("age", 0);
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");
        int age = extras.getInt("age");

        contentTv.setText("姓名：" + name + "，年龄：" + age);
    }

    public void back(View view) {
        Intent intent = new Intent();
        intent.putExtra("result", "我是第二个页面返回的数据");
        setResult(RESULT_OK, intent);
        finish();
    }
}