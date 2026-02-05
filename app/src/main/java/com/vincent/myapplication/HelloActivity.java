package com.vincent.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.vincent.myapplication.databinding.ActivityHelloBinding;

import jp.wasabeef.glide.transformations.BlurTransformation;


public class HelloActivity extends AppCompatActivity {

    private ActivityHelloBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityHelloBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Glide.with(HelloActivity.this)
                .load("https://picsum.photos/300?random=20")
                .into(binding.iv1);

        Glide.with(HelloActivity.this)
                .load("https://picsum.photos2/300?random=18")
                .error(R.drawable.error)
                .placeholder(R.drawable.loading)
                .into(binding.iv2);

        Glide.with(HelloActivity.this)
                .load(R.drawable.giphy)
                .into(binding.iv3);

        Glide.with(HelloActivity.this)
                .load("https://picsum.photos/300?random=22")
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(binding.iv4);

        Glide.with(HelloActivity.this)
                .load("https://picsum.photos/300?random=17")
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(16)))
                .into(binding.iv5);

        Glide.with(HelloActivity.this)
                .load("https://picsum.photos/300?random=20")
                .placeholder(R.drawable.loading)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 1)))
                .into(binding.iv6);

    }

}