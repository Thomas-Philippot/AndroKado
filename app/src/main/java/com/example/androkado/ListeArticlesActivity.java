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

import com.example.androkado.adapter.AdapterArticle;
import com.example.androkado.model.Article;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ListeArticlesActivity extends AppCompatActivity {

    ArrayList<Article> articles = new ArrayList<>();
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_articles);
        this.preferences = this.getSharedPreferences("PREFS_FILE", Context.MODE_PRIVATE);

        this.articles.add(new Article("nomAAA", "descAAA", 1.25, 1.5, "urlAAA", true));
        this.articles.add(new Article("nomBBB", "descBBB", 3.25, 2.4, "urlBBB", false));
        this.articles.add(new Article("nomCCC", "descCCC", 4.58, 3.5, "urlCCC", false));
        this.articles.add(new Article("nomDDD", "descDDD", 3.35, 3.8, "urlDDD", true));
        this.articles.add(new Article("nomEEE", "descEEE", 4.96, 4, "urlEEE", true));
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.sortArticles();
        AdapterArticle adapterArticle = new AdapterArticle(this, R.layout.row_style_article, this.articles);

        final ListView listeArticles = this.findViewById(R.id.lvArticles);
        listeArticles.setAdapter(adapterArticle);

        listeArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article articleClicked = articles.get(position);

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
                Collections.sort(articles, Article.priceComparator);
            } else {
                Collections.sort(articles, Article.nameComparator);
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
