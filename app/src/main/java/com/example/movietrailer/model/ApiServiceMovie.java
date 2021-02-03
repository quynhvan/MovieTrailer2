package com.example.movietrailer.model;

import com.example.database.Detail;
import com.example.movietrailer.model.movie.ApiResponseMovieCast;
import com.example.movietrailer.model.movie.ApiResponseMovieDetail;
import com.example.movietrailer.model.movie.ApiResponseMovieImages;
import com.example.movietrailer.model.movie.ApiResponseMovie;
import com.example.movietrailer.model.movie.ApiResponseMovieReview;
import com.example.movietrailer.model.movie.ApiResponseMovieVideo;
import com.example.movietrailer.model.show.ApiResponseShow;
import com.example.movietrailer.model.show.ApiResponseShowCast;
import com.example.movietrailer.model.show.ApiResponseShowDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServiceMovie {
    @GET("movie/popular?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<ApiResponseMovie> getListMoviePopular();

    @GET("movie/now_playing?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<ApiResponseMovie> getListMovieNowPlaying();

    @GET("movie/top_rated?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<ApiResponseMovie> getListMovieTopRate();

    @GET("movie/upcoming?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<ApiResponseMovie> getListMovieUpComing();

    @GET("movie/{movie_id}/recommendations?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<ApiResponseMovie> getListMovieRecommend(@Path("movie_id") int movie_id);

    @GET("movie/{movie_id}?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<ApiResponseMovieDetail> getDetailMovie(@Path("movie_id")int movie_id);


    @GET("movie/{movie_id}?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<Detail> getDetail(@Path("movie_id")int movie_id);

    @GET("movie/{movie_id}/credits?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<ApiResponseMovieCast> getListMovieCast(@Path("movie_id") int movie_id);

    @GET("movie/{movie_id}/similar?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<ApiResponseMovie> getListMovieSimilar(@Path("movie_id") int movie_id);

    @GET("movie/{movie_id}/images?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<ApiResponseMovieImages> getListMovieImages(@Path("movie_id") int movie_id);

    @GET("movie/{movie_id}/recommendations?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<ApiResponseMovie> getListRecommendaions();

    @GET("movie/{movie_id}/videos?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<ApiResponseMovieVideo> getListMovieVideo(@Path("movie_id") int movie_id);

    @GET("movie/{movie_id}/reviews?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<ApiResponseMovieReview> getListMovieReview(@Path("movie_id") int movie_id);

    //show

    @GET("tv/{tv_id}?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<ApiResponseShowDetail> getDetailShow(@Path("tv_id")int tv_id);

    @GET("tv/{tv_id}?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<Detail> DetailShow(@Path("tv_id")int tv_id);

    @GET("tv/{tv_id}/credits?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<ApiResponseMovieCast> getListShowCast(@Path("tv_id") int tv_id);

    @GET("tv/{tv_id}/reviews?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<ApiResponseMovieReview> getListShowReview(@Path("tv_id") int tv_id);

    @GET("tv/{tv_id}/images?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<ApiResponseMovieImages> getListShowImage(@Path("tv_id") int tv_id);

    @GET("tv/{tv_id}/videos?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<ApiResponseMovieVideo> getListShowVideo(@Path("tv_id") int tv_id);

    @GET("tv/popular?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<ApiResponseShow> getListShowPopular();

    @GET("tv/top_rated?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<ApiResponseShow> getListShowTopRate();

    @GET("tv/on_the_air?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<ApiResponseShow> getListShowOnTheAir();

    @GET("tv/airing_today?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<ApiResponseShow> getListShowAiringToday();

    @GET("tv/{tv_id}/similar?api_key=1757841baf6268f9c88701a5a512c4d2")
    Call<ApiResponseShow> getListShowSimilar(@Path("tv_id") int tv_id);


    // Youtub

}
