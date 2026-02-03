package com.vincent.java.ui.component;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vincent.java.R;

public class RadioButtonActivity extends AppCompatActivity {

    private RadioGroup rgCity;
    private TextView tvCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_radio_button);
        rgCity = findViewById(R.id.rg_city);
        tvCity = findViewById(R.id.city);

        rgCity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int i) {
                String city = "";
                switch(i) {
                    case R.id.beijing:
                        city = "北京";
                        break;
                    case R.id.shanghai:
                        city = "上海";
                        break;
                    case R.id.guangzhou:
                        city = "广州";
                        break;
                    case R.id.shenzhen:
                        city = "深圳";
                        break;
                    default:
                        break;
                }
                tvCity.setText(city);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}