package com.vincent.java;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vincent.java.databinding.ActivityHttpBinding;

import java.io.IOException;

import es.dmoral.toasty.Toasty;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class HttpActivity extends AppCompatActivity {

    private ActivityHttpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityHttpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void showData(View view) {
    }

    public void httpUrlConnection(View view) {
    }

    public void requestByOkHttp(View view) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://staging.synthesis.bz/jerirewards/api/mobile/shoppers/login";
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        e.printStackTrace();
                        binding.httpContent.setText(e.getMessage());
                        Toasty.error(HttpActivity.this, "Network error", Toast.LENGTH_LONG,true).show();
                    }
                });

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.isSuccessful()) {
                            // 响应结果
                            String res = null;
                            try {
                                res = response.body().toString();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            binding.httpContent.setText(res);
                        } else {
                            Toasty.warning(HttpActivity.this, "Network error", Toast.LENGTH_LONG,true).show();
                        }
                    }
                });

            }
        });
    }

    public void okhttpPost(View view) {
        String url = "https://staging.synthesis.bz/jerirewards/api/mobile/shoppers/login";
        FormBody body = new FormBody.Builder()
                .add("email", "example@qq.com")
                .add("password", "123123")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        e.printStackTrace();
                        binding.httpContent.setText(e.getMessage());
                        Toasty.error(HttpActivity.this, "Network error", Toast.LENGTH_LONG,true).show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.isSuccessful()) {
                            String res = null;
                            try {
                                res = response.body().toString();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            binding.httpContent.setText(res);
                            Toasty.success(HttpActivity.this, "Login succeed", Toast.LENGTH_LONG, true).show();
                        } else {
                            Toasty.warning(HttpActivity.this, "Network error", Toast.LENGTH_LONG,true).show();
                        }
                    }
                });
            }
        });
    }
}