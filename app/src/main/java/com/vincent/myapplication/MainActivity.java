package com.vincent.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.CombinedVibration;
import android.os.VibrationEffect;
import android.os.VibratorManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class MainActivity extends AppCompatActivity {

    private final String[] titles = {"标题1","标题2","标题3","标题4","标题5","标题6","标题7","标题8","标题9","标题10"};

    private final String[] descs = {
            "这一个描述内容","这一个描述内容",
            "这一个描述内容","这一个描述内容",
            "这一个描述内容","这一个描述内容",
            "这一个描述内容","这一个描述内容",
            "这一个描述内容","这一个描述内容"
    };

    private final String[] icons = {
      "https://picsum.photos/300?random=1",
      "https://picsum.photos/300?random=2",
      "https://picsum.photos/300?random=3",
      "https://picsum.photos/300?random=4",
      "https://picsum.photos/300?random=5",
      "https://picsum.photos/300?random=6",
      "https://picsum.photos/300?random=7",
      "https://picsum.photos/300?random=8",
      "https://picsum.photos/300?random=9",
      "https://picsum.photos/300?random=10"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RecyclerView listRv = findViewById(R.id.rv);
//        listRv.setLayoutManager(new LinearLayoutManager(this));
        listRv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        listRv.setAdapter(new MyAdapter());

        Button btn1 = findViewById(R.id.btn_1);
        Button btn2 = findViewById(R.id.btn_2);
        Button btn3 = findViewById(R.id.btn_3);
        Button btn4 = findViewById(R.id.btn_4);
        btn1.setOnClickListener(view -> vibrate(100));
        btn2.setOnClickListener(view -> vibrate(100));
        btn3.setOnClickListener(view -> vibrate(100));
        btn4.setOnClickListener(view -> vibrate(100));

    }

    private void vibrate( int duration) {
        VibratorManager vibrator = (VibratorManager) getSystemService(Context.VIBRATOR_MANAGER_SERVICE);
        vibrator.vibrate(CombinedVibration.createParallel(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE)));
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(MainActivity.this, R.layout.list_item,null);
            return new VH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            RequestOptions options = new RequestOptions().transform(new RoundedCorners(30));
            Glide
                    .with(MainActivity.this)
                    .load(icons[position])
                    .fitCenter()
                    .apply(options)
                    .into(holder.icon);
            holder.title.setText(titles[position]);
            holder.desc.setText(descs[position]);

            holder.icon.setOnLongClickListener(view -> {
                Toast.makeText(MainActivity.this, titles[position], Toast.LENGTH_LONG).show();
                vibrate(100);
                return true;
            });
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }

        class VH extends RecyclerView.ViewHolder {
            ImageView icon;
            TextView title;
            TextView desc;
            public VH(@NonNull View itemView) {
                super(itemView);
                icon = itemView.findViewById(R.id.iv);
                title = itemView.findViewById(R.id.tv_title);
                desc = itemView.findViewById(R.id.tv_desc);
            }
        }
    }
}