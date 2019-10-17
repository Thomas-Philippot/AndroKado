package com.example.androkado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.androkado.model.Article;

public class InfoUrlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_url);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Article article = intent.getParcelableExtra("article");

        TextView tvUrl = this.findViewById(R.id.url);
        tvUrl.setText(article.getUrl());
    }
}
