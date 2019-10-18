package com.example.androkado.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

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

    public void delete(Article article) {
        new AsyncDeleteArticle(articleDao).execute(article);
    }

    public void update(Article article) {
        new AsyncUpdateArticle(articleDao).execute(article);
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

    private static class AsyncDeleteArticle extends AsyncTask<Article, Void, Void> {
        private ArticleDao articleDao;
        AsyncDeleteArticle(ArticleDao articleDao) {
            this.articleDao = articleDao;
        }

        @Override
        protected Void doInBackground(Article... articles) {
            this.articleDao.delete(articles[0]);
            return null;
        }
    }

    private static class AsyncUpdateArticle extends AsyncTask<Article, Void, Void> {
        private ArticleDao articleDao;
        AsyncUpdateArticle(ArticleDao articleDao) {
            this.articleDao = articleDao;
        }

        @Override
        protected Void doInBackground(Article... articles) {
            Log.wtf("TOTO", "Update Async" + articles[0].toString());
            this.articleDao.update(articles[0]);
            return null;
        }
    }
}
