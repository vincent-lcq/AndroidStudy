package com.vincent.java.ui.component;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vincent.java.R;

public class AlertDialogActivity extends AppCompatActivity {

    private TextView text;
    private String[] titleArr = new String[]{"小号","默认","中号","大号","超大号"};
    private String[] hobbyArr = new String[]{"羽毛球","篮球","足球"};
    private boolean[] checkedArr = new boolean[]{false, false, false};
    private int[] textSizeArr = new int[]{10,20,30,40,50};

    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alert_dialog);
        text = findViewById(R.id.a_text);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void showNormalDialog(View view) {
        AlertDialog alertDialog =  new AlertDialog.Builder(this)
                .setTitle("温馨提示：")
                .setMessage("确定要退出登录吗？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AlertDialogActivity.this, "不行", Toast.LENGTH_LONG).show();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AlertDialogActivity.this, "好的", Toast.LENGTH_LONG).show();
                    }
                })
                .create();
        alertDialog.show();
    }

    public void showSingleChoiceDialog(View view) {
        new AlertDialog.Builder(this)
                .setTitle("请选择字体大小")
                .setSingleChoiceItems(titleArr, 0, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        index = which;
                    }
                })
                .setNegativeButton("取消",null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        text.setText(titleArr[index]);
                        text.setTextSize(textSizeArr[index]);
                    }
                }).create().show();
    }

    public void showMultiChoiceDialog(View view) {
        new AlertDialog.Builder(this)
                .setTitle("请选择兴趣爱好")
                .setMultiChoiceItems(hobbyArr, checkedArr, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedArr[which] = isChecked;
                    }
                })
                .setNegativeButton("取消",null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        StringBuffer stringbuffer = new StringBuffer();
                        for(int j = 0; j < checkedArr.length; j++) {
                            if(checkedArr[j]){
                                stringbuffer.append(hobbyArr[j]).append(" ");
                            }
                        }
                        Toast.makeText(AlertDialogActivity.this, "你选择的爱好是：" + stringbuffer.toString(), Toast.LENGTH_LONG).show();
                    }
                })
                .create()
                .show();
    }
}