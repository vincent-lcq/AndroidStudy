package com.vincent.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.vincent.myapplication.databinding.ActivityBannerBinding
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator

class BannerActivity : AppCompatActivity() {

    lateinit var binding: ActivityBannerBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBannerBinding.inflate(layoutInflater);
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.banner.addBannerLifecycleObserver(this)
            .setIndicator(CircleIndicator(this))
            .setAdapter(object: BannerImageAdapter<String>(Constants.bannerList){
                override fun onBindView(
                    holder: BannerImageHolder,
                    url: String?,
                    position: Int,
                    size: Int
                ) {
                    Glide.with(this@BannerActivity).load(url).into(holder.imageView)
                }
            })
    }
}