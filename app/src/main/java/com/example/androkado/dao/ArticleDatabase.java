package com.example.androkado.dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.androkado.model.Article;

@Database(entities = {Article.class}, version = 1)
public abstract class ArticleDatabase extends RoomDatabase {

    private static ArticleDatabase INSTANCE;
    public abstract ArticleDao articleDao();

    public static synchronized ArticleDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, ArticleDatabase.class, "artilces.db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return INSTANCE;
    }

    private static Callback roomCallBack = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateBddAsync().execute(INSTANCE);
        }
    };

    private static class PopulateBddAsync extends AsyncTask<ArticleDatabase,Void,Void> {
        @Override
        protected Void doInBackground(ArticleDatabase... articleDatabases) {
            ArticleDao dao = articleDatabases[0].articleDao();
            dao.insert(new Article("Raid Shadow Legends", "Un jeu mobile qui vend son cul", 1.25, 2, "urlAAA", true));
            return null;
        }
    }
}
