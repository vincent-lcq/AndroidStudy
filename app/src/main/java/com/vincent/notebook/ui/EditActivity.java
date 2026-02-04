package com.vincent.notebook.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vincent.notebook.R;
import com.vincent.notebook.bean.Notepad;
import com.vincent.notebook.databinding.ActivityEditBinding;
import com.vincent.notebook.db.MyDbHelper;
import com.vincent.notebook.utils.TimeUtil;

import java.io.Serializable;

import es.dmoral.toasty.Toasty;

public class EditActivity extends AppCompatActivity {

    private ActivityEditBinding binding;

    private MyDbHelper myDebHelper;

    private TimeUtil timeUtil;

    private Notepad notepad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(40, systemBars.top, 40, systemBars.bottom);
            return insets;
        });
        myDebHelper = new MyDbHelper(this);
        Serializable serializable = getIntent().getSerializableExtra("notepad");
        if (serializable == null) {
            binding.titleTv.setText("添加记录");
        } else {
            binding.titleTv.setText("修改记录");
            notepad = (Notepad) serializable;
            binding.contentEt.setText(notepad.getContent());
        }
        binding.backIv.setOnClickListener(view -> finish());
        binding.clearIv.setOnClickListener(view ->  binding.contentEt.setText(""));

        binding.saveIv.setOnClickListener(view -> {
            String content =  binding.contentEt.getText().toString().trim();
            if(TextUtils.isEmpty(content)) {
                Toasty.warning(this,"请输入内容", Toast.LENGTH_SHORT, true).show();
                return;
            }

            // 判断新增还是修改
            if (notepad == null) {
                notepad = new Notepad();
                notepad.setContent(content);
                notepad.setTime(TimeUtil.getTime());
                myDebHelper.insert(notepad);
                Toasty.success(this, "创建成功", Toast.LENGTH_LONG,true).show();
            } else {
                notepad.setContent(content);
                notepad.setTime(TimeUtil.getTime());
                myDebHelper.update(notepad);
                Toasty.success(this, "操作成功", Toast.LENGTH_LONG,true).show();
            }
            setResult(RESULT_OK);
            finish();
        });
    }
}