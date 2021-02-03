package com.example.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.movietrailer.model.movie.ApiResponseMovieDetail;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addToFavList(Detail detail);

    @Delete
    void RemoveFromFavList(Detail detail);

    @Query("SELECT*FROM table_demo")
    LiveData<List<Detail>> getListFav();

    @Query("SELECT * FROM table_demo WHERE id = :itemId")
    Detail getMovieById(int itemId);

    @Query("DELETE FROM table_demo")
    void deleteAllMovies();

}
