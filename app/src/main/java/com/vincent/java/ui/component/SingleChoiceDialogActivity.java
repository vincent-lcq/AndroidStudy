package com.vincent.java.ui.component;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vincent.java.R;

public class SingleChoiceDialogActivity extends AppCompatActivity {

    private String[] titleArr = new String[]{"小","中","大"};
    private int[] textSizeArr = new int[]{12,18,22};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_single_choice_dialog);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void showSelect(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("请选择字体大小")
                .setSingleChoiceItems(titleArr, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("确定", null)
                .setNegativeButton("取消", null)
                .create();
        alertDialog.show();
    }
}