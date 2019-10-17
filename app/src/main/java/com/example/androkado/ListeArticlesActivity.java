package com.example.androkado;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.androkado.adapter.AdapterArticle;
import com.example.androkado.model.Article;
import com.example.androkado.view_model.ArticleViewModel;
import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListeArticlesActivity extends AppCompatActivity {

    List<Article> articleArrayList = new ArrayList<>();
    private SharedPreferences preferences;
    private ArticleViewModel articleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_articles);
        this.preferences = this.getSharedPreferences("PREFS_FILE", Context.MODE_PRIVATE);

        this.articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
        Stetho.initializeWithDefaults(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.sortArticles();

        final ListView listeArticles = findViewById(R.id.lvArticles);

        this.articleViewModel.findAll().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                articleArrayList = articles;
                AdapterArticle adapterArticle = new AdapterArticle(ListeArticlesActivity.this, R.layout.row_style_article, articles);
                listeArticles.setAdapter(adapterArticle);
            }
        });

        listeArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article articleClicked = articleArrayList.get(position);

                Intent intent = new Intent(ListeArticlesActivity.this, MainActivity.class);
                intent.putExtra("article", articleClicked);
                startActivity(intent);
            }
        });
    }

    private void sortArticles() {
        String SORT = "sort";

        if (preferences.contains(SORT)) {
            if (preferences.getBoolean(SORT, false)) {
                Collections.sort(articleArrayList, Article.priceComparator);
            } else {
                Collections.sort(articleArrayList, Article.nameComparator);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_configuration:
                Intent intent = new Intent(this, ConfigurationActivity.class);
                this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
