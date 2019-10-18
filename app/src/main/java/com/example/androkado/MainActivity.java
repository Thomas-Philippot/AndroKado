package com.example.androkado;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.androkado.model.Article;
import com.example.androkado.view_model.ArticleViewModel;

public class MainActivity extends AppCompatActivity {

    Article article;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        this.article = intent.getParcelableExtra("article");
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();

        TextView tvNom = this.findViewById(R.id.nom);
        TextView tvDescription = this.findViewById(R.id.description);
        TextView tvPrix = this.findViewById(R.id.prix);
        RatingBar rbDegreEnvie = this.findViewById(R.id.degreEnvie);
        ToggleButton btnAchat = this.findViewById(R.id.btnAchete);

        tvNom.setText(this.article.getNom());
        tvDescription.setText(this.article.getDescription());
        tvPrix.setText(this.article.getPrix() + " €");
        rbDegreEnvie.setRating(Float.parseFloat(String.valueOf(this.article.getDegreEnvie())));
        btnAchat.setChecked(this.article.isAchete());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.article_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_article_action:
                Intent articleEditIntent = new Intent(this, ArticleEditActivity.class);
                articleEditIntent.putExtra("article", this.article);
                this.startActivity(articleEditIntent);
                break;
            case R.id.delete_article_action:
                ArticleViewModel articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
                articleViewModel.delete(this.article);
                Intent homeIntent = new Intent(this, ListeArticlesActivity.class);
                this.finishAffinity();
                this.startActivity(homeIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickUrl(View view) {
        Intent intent = new Intent(this, InfoUrlActivity.class);
        intent.putExtra("article", article);
        this.startActivity(intent);
    }
}
