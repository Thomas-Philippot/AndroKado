package com.example.androkado.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androkado.model.Article;

import java.util.List;

@Dao
public interface ArticleDao {


    @Query("SELECT * FROM Article")
    LiveData<List<Article>> findAll();

    @Query("SELECT * FROM Article WHERE uid = :id")
    Article findById(int id);

    @Insert
    void insert(Article article);

    @Update
    void update(Article article);

    @Delete
    void delete(Article article);

    @Query("DELETE from article")
    void deleteAll();

}
