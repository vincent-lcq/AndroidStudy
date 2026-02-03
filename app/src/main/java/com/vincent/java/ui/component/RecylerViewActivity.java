package com.vincent.java.ui.component;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vincent.java.R;

public class RecylerViewActivity extends AppCompatActivity {

    private RecyclerView listRv;

    private String[] names = {"小猫","小狗","小鸭","小鹿","小老虎"};

    private int[] icons = {R.drawable.cat, R.drawable.dog, R.drawable.duck, R.drawable.deer, R.drawable.tiger};

    private String[] introduces ={
            "小猫小猫小猫小猫小猫小猫小猫小猫小猫小猫",
            "小狗小狗小狗小狗小狗小狗小狗小狗小狗小狗",
            "小鸭小鸭小鸭小鸭小鸭小鸭小鸭小鸭小鸭小鸭",
            "小鹿小鹿小鹿小鹿小鹿小鹿小鹿小鹿小鹿小鹿",
            "小老虎小老虎小老虎小老虎小老虎小老虎小老虎小老虎小老虎"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recyler_view);

        listRv = findViewById(R.id.recyclerView);
        listRv.setLayoutManager(new LinearLayoutManager(this));
        listRv.setAdapter(new MyAdapter());

        // 设置点击事件
        onItemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(RecylerViewActivity.this, names[position], Toast.LENGTH_LONG).show();
            }
        };
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    // 创建Adapter
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        // 创建ViewHolder
        @NonNull
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(RecylerViewActivity.this, R.layout.item_b_list,null);
            return new ViewHolder(view);
        }

        // 绑定数据
        @Override
        public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
            holder.iconIv.setImageResource(icons[position]);
            holder.nameTv.setText(names[position]);
            holder.introduceTv.setText(introduces[position]);

            // 添加点击事件
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null) {
                        onItemClickListener.onItemClick(holder.itemView, position);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return names.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView iconIv;
            TextView nameTv,introduceTv;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                iconIv = itemView.findViewById(R.id.iv);
                nameTv = itemView.findViewById(R.id.name);
                introduceTv = itemView.findViewById(R.id.introduce);
            }

        }

    }

    private OnItemClickListener onItemClickListener;

    interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}