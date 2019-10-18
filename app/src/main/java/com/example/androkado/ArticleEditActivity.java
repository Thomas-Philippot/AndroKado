package com.example.androkado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.androkado.model.Article;
import com.example.androkado.view_model.ArticleViewModel;

public class ArticleEditActivity extends AppCompatActivity {

    private ArticleViewModel articleViewModel;
    private Article article;

    EditText eTTitle;
    EditText eTDescription;
    EditText eTPrice;
    RatingBar eTRating;
    EditText eTUrl;
    double defaultPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_edit);
        SharedPreferences preferences = this.getSharedPreferences("PREFS_FILE", Context.MODE_PRIVATE);
        this.defaultPrice = Double.valueOf(preferences.getString(ConfigurationActivity.PRICE, ""));
        Intent intent = getIntent();
        this.article = intent.getParcelableExtra("article");
        this.articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);

        eTTitle = this.findViewById(R.id.edit_article_title);
        eTDescription = this.findViewById(R.id.edit_article_description);
        eTPrice = this.findViewById(R.id.edit_article_price);
        eTRating = this.findViewById(R.id.edit_article_rating);
        eTUrl = this.findViewById(R.id.edit_article_url);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.article != null) {
            eTTitle.setText(this.article.getNom());
            eTDescription.setText(this.article.getDescription());
            eTPrice.setText(String.valueOf(this.article.getPrix()));
            eTRating.setRating(Float.valueOf(String.valueOf(this.article.getPrix())));
            eTUrl.setText(this.article.getUrl());
            Button btnSubmit = this.findViewById(R.id.button_article_submit);
            btnSubmit.setText("Modifier");
        }
    }

    public void onClickSubmit(View view) {

            String title = eTTitle.getText().toString();
            String description = eTDescription.getText().toString();
            double price = this.defaultPrice;
            if (!eTPrice.getText().toString().isEmpty()) {
                price = Double.valueOf(eTPrice.getText().toString());
            }
            double rating = Double.valueOf(String.valueOf(eTRating.getRating()));
            String url = eTUrl.getText().toString();

        if (this.article != null) {
            this.article.setNom(title);
            this.article.setDescription(description);
            this.article.setPrix(price);
            this.article.setDegreEnvie(rating);
            this.article.setUrl(url);
            this.articleViewModel.update(this.article);
            Intent intent = new Intent(this, ListeArticlesActivity.class);
            this.finishAffinity();
            this.startActivity(intent);
        } else {
            this.article = new Article(title, description, price, rating, url, false);
            this.articleViewModel.insert(this.article);
            this.finish();
        }
    }
}
