package com.vincent.java.ui.component;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.vincent.java.MainActivity;
import com.vincent.java.R;

public class ListViewActivity extends AppCompatActivity {

    private String[] titles = new String[]{"桌子","苹果","蛋糕","线衣","猕猴桃","围巾"};
    private String[] prices = new String[]{"1800元","10元/kg","300元","350元","10元/kg","280元"};
    private int[] icons = new int[]{R.drawable.table, R.drawable.apple, R.drawable.cake,
            R.drawable.wireclothes, R.drawable.kiwifruit, R.drawable.scarf};

    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(40, systemBars.top, 40, systemBars.bottom);
            return insets;
        });

        // 1. 获取ListView对象
        mListView = findViewById(R.id.list_view);
        MyAdapter myAdapter = new MyAdapter(); // 创建Adapter
        mListView.setAdapter(myAdapter); // 添加Adapter

        // 单击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(
                        ListViewActivity.this,
                        "标题：" + titles[position] + "，价格：" + prices[position],
                        Toast.LENGTH_LONG
                ).show();
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this, titles[position] + "长按了", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return titles.length;
        };

        @Override
        public Object getItem(int position) {
            return titles[position];
        };

        @Override
        public long getItemId(int position) {
            return position;
        };

//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            // 1. 加载布局
//            View view = View.inflate(ListViewActivity.this, R.layout.item_list,null);
//
//            // 2. 获取控件
//            ImageView iv = view.findViewById(R.id.iv);
//            TextView title = view.findViewById(R.id.title);
//            TextView price = view.findViewById(R.id.price);
//
//            // 3. 设置数据
//            iv.setImageResource(icons[position]);
//            title.setText(titles[position]);
//            price.setText(prices[position]);
//            RecyclerView.ViewHolder vh;
//
//            return view;
//        };

        // 优化Adapter
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            VH holder;
            if(convertView == null) {
                // 1. 加载布局
                convertView = View.inflate(ListViewActivity.this, R.layout.item_list,null);
                holder = new VH();
                // 2. 获取控件
                holder.iv = convertView.findViewById(R.id.iv);
                holder.title = convertView.findViewById(R.id.title);
                holder.price = convertView.findViewById(R.id.price);
                convertView.setTag(holder);
            } else {
                holder = (VH) convertView.getTag();
            }

            holder.iv.setImageResource(icons[position]);
            holder.title.setText(titles[position]);
            holder.price.setText(prices[position]);

            return convertView;
        };

        class VH {
            ImageView iv;
            TextView title;
            TextView price;
        }
    }
}