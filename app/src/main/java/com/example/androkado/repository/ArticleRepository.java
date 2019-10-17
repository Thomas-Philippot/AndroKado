package com.example.androkado.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.androkado.dao.ArticleDao;
import com.example.androkado.dao.ArticleDatabase;
import com.example.androkado.model.Article;

import java.util.List;

public class ArticleRepository {

    private ArticleDao articleDao;
    private LiveData<List<Article>> articles;

    public ArticleRepository(Application application) {
        ArticleDatabase articleDatabase = ArticleDatabase.getInstance(application);
        articleDao = articleDatabase.articleDao();
        articles = articleDao.findAll();
    }

    public void insert(Article article) {
        new AsyncInsertArticle(articleDao).execute(article);
    }

    public LiveData<List<Article>> findAll() {
        return articles;
    }

    private static class AsyncInsertArticle extends AsyncTask<Article, Void, Void> {
        private ArticleDao articleDao;

        AsyncInsertArticle(ArticleDao articleDao) {
            this.articleDao = articleDao;
        }

        @Override
        protected Void doInBackground(Article... articles) {
            this.articleDao.insert(articles[0]);
            return null;
        }
    }
}
