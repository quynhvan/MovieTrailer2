package com.example.movietrailer.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.movietrailer.R;
import com.example.movietrailer.adapter.movie.AdapterCategoryMovie;
import com.example.movietrailer.adapter.movie.AdapterSlideMovie;
import com.example.movietrailer.model.ConnectServer;
import com.example.movietrailer.model.movie.ApiResponseMovie;
import com.example.movietrailer.model.movie.CategoryMovie;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends Fragment {
    private RecyclerView recyclerView;
    private AdapterCategoryMovie adapterCategoryMovie;
    private AdapterSlideMovie adapterSlideMovie;
    private ArrayList<CategoryMovie> listCategory = new ArrayList<>(3);
    private ArrayList<ApiResponseMovie.Movie> listPopular = new ArrayList<>();
    private ArrayList<ApiResponseMovie.Movie> listTopRate = new ArrayList<>();
    private ArrayList<ApiResponseMovie.Movie> listNowPlaying = new ArrayList<>();
    private ArrayList<ApiResponseMovie.Movie> listUpComming = new ArrayList<>();
    private ViewPager slide;
    private DotsIndicator dot;
    private ArrayList<ApiResponseMovie.Movie> listSlide = new ArrayList<>();
    private ArrayList<ApiResponseMovie.Movie> mlistSlide = new ArrayList<>();
    private Timer timer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        recyclerView = view.findViewById(R.id.re_category);
        slide = view.findViewById(R.id.fragment_slide);
        dot = view.findViewById(R.id.dot);
        initSlide();
        initShow();
        return view;
    }

    private void initSlide() {
        adapterSlideMovie = new AdapterSlideMovie(getContext(), mlistSlide);
        slide.setAdapter(adapterSlideMovie);
        dot.setViewPager(slide);
        autoSlide();
    }

    private void autoSlide() {
        if (mlistSlide == null || mlistSlide.isEmpty() || slide == null) {
            return;
        }
        // init timer
        if (timer == null) {
            timer = new Timer(); // khởi tạo timer
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = slide.getCurrentItem();
                        int totalItem = mlistSlide.size() - 1;
                        if (currentItem < totalItem) {
                            currentItem++;
                            slide.setCurrentItem(currentItem);
                        } else {
                            slide.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);
    }

    // nếu như activity này không tồn tại nữa thì hủy timer đi
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void initShow() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapterCategoryMovie = new AdapterCategoryMovie(getContext(), listCategory);
        recyclerView.setAdapter(adapterCategoryMovie);
        callApiMoviePopular();
        callApiMovieTopRate();
        callApiMovieNowPlaying();
        callApiMovieUpComing();
    }

    private void callApiMoviePopular() {

        ConnectServer.getApiService().getListMoviePopular().enqueue(new Callback<ApiResponseMovie>() {
            @Override
            public void onResponse(Call<ApiResponseMovie> call, Response<ApiResponseMovie> response) {
                if (response.isSuccessful()) {
                    ApiResponseMovie apiResponseMovie = response.body();
                    listPopular.addAll(apiResponseMovie.getResults());
                    listSlide.addAll(apiResponseMovie.getResults());
                    initgetLimitSlide();
                    listCategory.add(new CategoryMovie("Popular", listPopular));
                    adapterCategoryMovie.notifyDataSetChanged();
                    adapterSlideMovie.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseMovie> call, Throwable t) {

            }
        });
    }

    private void initgetLimitSlide() {
        for (int i = 0; i < 4; i++) {
            mlistSlide.add(listSlide.get(i));
        }
    }

    private void callApiMovieTopRate() {
        ConnectServer.getApiService().getListMovieTopRate().enqueue(new Callback<ApiResponseMovie>() {
            @Override
            public void onResponse(Call<ApiResponseMovie> call, Response<ApiResponseMovie> response) {
                if (response.isSuccessful()) {
                    ApiResponseMovie apiResponseMovie = response.body();
                    listTopRate.addAll(apiResponseMovie.getResults());
                    listCategory.add(new CategoryMovie("Top Rated", listTopRate));
                    adapterCategoryMovie.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseMovie> call, Throwable t) {

            }
        });
    }

    private void callApiMovieNowPlaying() {
        ConnectServer.getApiService().getListMovieNowPlaying().enqueue(new Callback<ApiResponseMovie>() {
            @Override
            public void onResponse(Call<ApiResponseMovie> call, Response<ApiResponseMovie> response) {
                if (response.isSuccessful()) {
                    ApiResponseMovie apiResponseMovie = response.body();
                    listNowPlaying.addAll(apiResponseMovie.getResults());
                    listCategory.add(new CategoryMovie("Now Playing", listNowPlaying));
                    adapterCategoryMovie.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseMovie> call, Throwable t) {

            }
        });
    }

    private void callApiMovieUpComing() {
        ConnectServer.getApiService().getListMovieUpComing().enqueue(new Callback<ApiResponseMovie>() {
            @Override
            public void onResponse(Call<ApiResponseMovie> call, Response<ApiResponseMovie> response) {
                if (response.isSuccessful()) {
                    ApiResponseMovie apiResponseMovie = response.body();
                    listUpComming.addAll(apiResponseMovie.getResults());
                    listCategory.add(new CategoryMovie("Up Coming", listUpComming));
                    adapterCategoryMovie.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseMovie> call, Throwable t) {

            }
        });
    }
}
