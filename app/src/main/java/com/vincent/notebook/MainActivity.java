package com.vincent.notebook;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.vincent.notebook.adapter.NotepadListAdapter;
import com.vincent.notebook.bean.Notepad;
import com.vincent.notebook.databinding.ActivityMainBinding;
import com.vincent.notebook.db.MyDbHelper;
import com.vincent.notebook.ui.EditActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    public MyDbHelper myDbHelper;

    private NotepadListAdapter notepadListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        myDbHelper = new MyDbHelper(this);
        binding.addIv.setOnClickListener( view -> {
//            Intent intent = new Intent(this, EditActivity.class);
//            startActivity(intent);
            startActivityForResult(new Intent(MainActivity.this, EditActivity.class), 100);
        });

        notepadListAdapter = new NotepadListAdapter();
        binding.listRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.listRv.setAdapter(notepadListAdapter);
//        binding.listRv.setLayoutManager(new LinearLayoutManager(this));

        findAll();
    }

//     @Override
//     protected void onResume() {
//        super.onResume();
//        findAll();
//     }

    public void findAll() {
        List<Notepad> notepadlist = myDbHelper.findAll();
        notepadListAdapter.update(notepadlist);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK) {
            findAll();
        }
    }
}