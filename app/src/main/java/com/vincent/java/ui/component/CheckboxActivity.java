package com.vincent.java.ui.component;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vincent.java.R;

import java.util.ArrayList;
import java.util.List;

public class CheckboxActivity extends AppCompatActivity {

    private CheckBox chang, tiao, rap;
    private Button button;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_checkbox);
        chang = findViewById(R.id.chang);
        tiao = findViewById(R.id.tiao);
        rap = findViewById(R.id.rap);
        button = findViewById(R.id.button);
        text  = findViewById(R.id.text);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(40, systemBars.top, 40, systemBars.bottom);
            return insets;
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> selectedHobby = new ArrayList<>();
                if(chang.isChecked()) {
                    selectedHobby.add(chang.getText().toString());
                }
                if(tiao.isChecked()) {
                    selectedHobby.add(tiao.getText().toString());
                }
                if(rap.isChecked()) {
                    selectedHobby.add(rap.getText().toString());
                }

                if(selectedHobby.isEmpty()) {
                    Toast.makeText(CheckboxActivity.this, "请至少选择一个爱好", Toast.LENGTH_LONG).show();
                    return;
                }

                text.setText(selectedHobby.toString());
            }
        });
    }
}