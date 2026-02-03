package com.vincent.java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_b);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void jumpb(View view) {
        // 方法一 putExtra
//        Intent intent = new Intent(this, ActivityC.class);
//        intent.putExtra("name", "张三");
//        intent.putExtra("age", 18);
//        startActivity(intent);
        
        // 方法二
        Intent intent = new Intent(this, ActivityC.class);
        Bundle bundle = new Bundle();
        bundle.putString("name", "李四");
        bundle.putInt("age", 18);
        intent.putExtras(bundle);
//        startActivity(intent);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            String result = data.getStringExtra("result");
            Toast.makeText(this, result.toString(), Toast.LENGTH_LONG).show();
        }
    }
}