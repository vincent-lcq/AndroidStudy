package com.vincent.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil3.load
import coil3.request.crossfade
import coil3.request.placeholder
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import coil3.transform.RoundedCornersTransformation
import com.vincent.myapplication.databinding.ActivityCoilBinding

class CoilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoilBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCoilBinding.inflate(layoutInflater);
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        binding.iv1.load(Constants.src1);
        binding.iv1.load(R.drawable.giphy);
        binding.iv2.load(Constants.src2) {
            crossfade(2000)
            placeholder(R.drawable.loading)
        }
        binding.iv3.load(Constants.src3) {
            transformations(CircleCropTransformation())
        }

        binding.iv4.load(Constants.src4) {
            transformations(RoundedCornersTransformation(10f))
        }
    }
}