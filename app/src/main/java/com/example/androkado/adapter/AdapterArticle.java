package com.example.androkado.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.androkado.R;
import com.example.androkado.model.Article;

import java.util.List;

public class AdapterArticle extends ArrayAdapter<Article> {
    private int monArticleLigne;

    public AdapterArticle(@NonNull Context context, int resource, @NonNull List<Article> objects) {
        super(context, resource, objects);
        monArticleLigne = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(monArticleLigne,parent,false);
        }

        TextView tvNom = convertView.findViewById(R.id.nom);
        TextView tvPrice = convertView.findViewById(R.id.price);

        Article articleToShow = getItem(position);

        tvNom.setText(articleToShow.getNom());
        tvPrice.setText(articleToShow.getPrix() + "€");

        return convertView;
    }
}
