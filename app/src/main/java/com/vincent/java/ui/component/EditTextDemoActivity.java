package com.vincent.java.ui.component;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vincent.java.R;

public class EditTextDemoActivity extends AppCompatActivity {

    private EditText editText;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_text_demo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(40, systemBars.top, 40, systemBars.bottom);
            return insets;
        });
        editText = findViewById(R.id.uname);
        password = findViewById(R.id.pwd);
    }

    public void login(View view) {
        String uname = editText.getText().toString().trim();
        String pwd = password.getText().toString().trim();
        if(TextUtils.isEmpty(uname)) {
            Toast.makeText(EditTextDemoActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(pwd)) {
            Toast.makeText(EditTextDemoActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }

        if(pwd.length() < 6 || pwd.length() > 12) {
            Toast.makeText(EditTextDemoActivity.this, "密码必须在 >6<12 之间",Toast.LENGTH_LONG).show();
        }

        Toast.makeText(EditTextDemoActivity.this, uname, Toast.LENGTH_LONG).show();
    }
}