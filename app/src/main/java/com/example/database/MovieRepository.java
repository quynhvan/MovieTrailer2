package com.example.database;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movietrailer.model.ConnectServer;
import com.example.movietrailer.model.movie.ApiResponseMovieDetail;

import java.util.List;

import io.reactivex.Completable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private MovieDAO mMovieDao;
    private LiveData<List<Detail>> mallMovie;

    public MovieRepository(Application application){
        MovieDatabase db = MovieDatabase.getDatabase(application);
        mMovieDao = db.MovieDao();
        mallMovie = mMovieDao.getListFav();
    }

    public LiveData<List<Detail>> getAllFav() {
        return mMovieDao.getListFav();
    }

    void RemoveFavList(Detail detail){
            mMovieDao.RemoveFromFavList(detail);
        }

    public void addFavList(Detail detail) {
            mMovieDao.addToFavList(detail);
    }
    public Detail getMovieById(int id){
         return mMovieDao.getMovieById(id);
    }
    public void DeleteAll(){
         mMovieDao.deleteAllMovies();
    }

//    public LiveData<Detail> getMovieDetails(int movieId) {
//        final MutableLiveData<Detail> movieDetailsData = new MutableLiveData<>();
//        ConnectServer.getApiService().getDetail(movieId).enqueue(new Callback<Detail>() {
//            @Override
//            public void onResponse(Call<Detail> call, Response<Detail> response) {
//                if (response.isSuccessful()) {
//                    Detail movieDetails = response.body();
//                    movieDetailsData.setValue(movieDetails);
//                }
//            }
//            @Override
//            public void onFailure(Call<Detail> call, Throwable t) {
//                movieDetailsData.setValue(null);
//
//            }
//        });
//        return movieDetailsData;
//    }
}
