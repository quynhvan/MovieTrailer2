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
import com.example.movietrailer.adapter.show.AdapterShowCategory;
import com.example.movietrailer.adapter.show.AdapterShowSlide;
import com.example.movietrailer.model.ConnectServer;
import com.example.movietrailer.model.show.ApiResponseShow;
import com.example.movietrailer.model.show.CategoryShow;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowActivity extends Fragment {
    private RecyclerView recyclerView;
    private AdapterShowCategory adapterShowCategory;
    private AdapterShowSlide adapterShowSlide;
    private ArrayList<CategoryShow> listCategory = new ArrayList<>(3);
    private ArrayList<ApiResponseShow.Show> listPopular = new ArrayList<>();
    private ArrayList<ApiResponseShow.Show> listTopRate = new ArrayList<>();
    private ArrayList<ApiResponseShow.Show> listAiringToday = new ArrayList<>();
    private ArrayList<ApiResponseShow.Show> listOnTheAir = new ArrayList<>();
    private ViewPager slide;
    private DotsIndicator dot;
    private ArrayList<ApiResponseShow.Show> listSlide = new ArrayList<>();
    private Timer timer;
    private String category;

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
        adapterShowSlide = new AdapterShowSlide(getContext(), listSlide);
        slide.setAdapter(adapterShowSlide);
        dot.setViewPager(slide);
        autoSlide();
    }

    private void autoSlide() {
        if (listSlide == null || listSlide.isEmpty() || slide == null) {
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
                        int totalItem = listSlide.size() - 1;
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
        adapterShowCategory = new AdapterShowCategory(getContext(), listCategory);
        recyclerView.setAdapter(adapterShowCategory);
        callApiShowPopular();
        callApiMovieTopRate();
        callApiShowOnTheAir();
        callApiShowAiringToday();
    }

    private void callApiShowPopular() {
        ConnectServer.getApiService().getListShowPopular().enqueue(new Callback<ApiResponseShow>() {
            @Override
            public void onResponse(Call<ApiResponseShow> call, Response<ApiResponseShow> response) {
                if (response.isSuccessful()) {
                    ApiResponseShow apiResponseShow = response.body();
                    listPopular.addAll(apiResponseShow.getResults());
                    listSlide.addAll(apiResponseShow.getResults());
                    initgetLimitSlide();
                    listCategory.add(new CategoryShow("Popular", listPopular));
                    adapterShowCategory.notifyDataSetChanged();
                    adapterShowSlide.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseShow> call, Throwable t) {

            }
        });
    }
    private void initgetLimitSlide() {
        for (int i = 0; i < 4; i++) {
            listSlide.add(listSlide.get(i));
        }
    }
    private void callApiMovieTopRate() {
        ConnectServer.getApiService().getListShowTopRate().enqueue(new Callback<ApiResponseShow>() {
            @Override
            public void onResponse(Call<ApiResponseShow> call, Response<ApiResponseShow> response) {
                if (response.isSuccessful()) {
                    ApiResponseShow apiResponseShow = response.body();
                    listTopRate.addAll(apiResponseShow.getResults());
                    listCategory.add(new CategoryShow("Top Rated", listTopRate));
                    adapterShowCategory.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseShow> call, Throwable t) {

            }
        });
    }

    private void callApiShowOnTheAir() {
        ConnectServer.getApiService().getListShowOnTheAir().enqueue(new Callback<ApiResponseShow>() {
            @Override
            public void onResponse(Call<ApiResponseShow> call, Response<ApiResponseShow> response) {
                if (response.isSuccessful()) {
                    ApiResponseShow apiResponseShow = response.body();
                    listOnTheAir.addAll(apiResponseShow.getResults());
                    listCategory.add(new CategoryShow("On the Air", listOnTheAir));
                    adapterShowCategory.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseShow> call, Throwable t) {

            }
        });
    }

    private void callApiShowAiringToday() {
        ConnectServer.getApiService().getListShowAiringToday().enqueue(new Callback<ApiResponseShow>() {
            @Override
            public void onResponse(Call<ApiResponseShow> call, Response<ApiResponseShow> response) {
                if (response.isSuccessful()) {
                    ApiResponseShow apiResponseShow = response.body();
                    listAiringToday.addAll(apiResponseShow.getResults());
                    listCategory.add(new CategoryShow("Airing Today", listAiringToday));
                    adapterShowCategory.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseShow> call, Throwable t) {

            }
        });
    }
}
