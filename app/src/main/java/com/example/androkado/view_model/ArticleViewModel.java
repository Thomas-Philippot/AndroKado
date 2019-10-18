package com.example.androkado.view_model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.androkado.model.Article;
import com.example.androkado.repository.ArticleRepository;

import java.util.List;

public class ArticleViewModel extends AndroidViewModel {

    private ArticleRepository articleRepository;
    private LiveData<List<Article>> articles;

    public ArticleViewModel(Application application) {
        super(application);
        this.articleRepository = new ArticleRepository(application);
        init();
    }

    private void init() {
        this.articles = articleRepository.findAll();
    }

    public LiveData<List<Article>> findAll() {
        return articles;
    }

    public void delete(Article article){
        this.articleRepository.delete(article);
    }

    public void insert(Article article) {
        this.articleRepository.insert(article);
    }

    public void update(Article article) {
        this.articleRepository.update(article);
    }
}
