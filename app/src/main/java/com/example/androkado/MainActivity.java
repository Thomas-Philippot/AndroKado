package com.example.androkado;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androkado.model.Article;

public class MainActivity extends AppCompatActivity {

    Article article;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        this.article = intent.getParcelableExtra("article");

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

    public void onClickUrl(View view) {
        Intent intent = new Intent(this, InfoUrlActivity.class);
        intent.putExtra("article", article);
        startActivity(intent);
    }
}
