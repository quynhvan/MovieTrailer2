package com.example.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movietrailer.model.movie.ApiResponseMovie;
import com.example.movietrailer.model.movie.ApiResponseMovieDetail;

import java.util.List;

import io.reactivex.Completable;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository repositoryFav;
//    private MovieDatabase movieDatabase;
//    private LiveData<List<Detail>> movies;
//    private Detail detail;


    public MovieViewModel(@NonNull Application application) {
        super(application);
        repositoryFav = new MovieRepository(application);
//        movies = repositoryFav.getAllFav();
//        movieDatabase = MovieDatabase.getDatabase(application);
    }

    public void RemoveFavList(Detail movie) {
        repositoryFav.RemoveFavList(movie);
    }

    public void addFavList(Detail movie){
        repositoryFav.addFavList(movie);
    }

    public boolean isFavorite(int isFavId){
        return repositoryFav.getMovieById(isFavId) != null;
    }

    public void deleteAllMovies() {
         repositoryFav.DeleteAll();
    }

    public LiveData<List<Detail>> getAllMovies() {
        return repositoryFav.getAllFav();
    }

}
