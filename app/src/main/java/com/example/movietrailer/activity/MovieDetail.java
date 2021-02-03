package com.example.movietrailer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.database.Detail;
import com.example.database.MovieDatabase;
import com.example.database.MovieViewModel;
import com.example.movietrailer.Constants;
import com.example.movietrailer.R;
import com.example.movietrailer.adapter.movie.AdapterCategoryMovie;
import com.example.movietrailer.adapter.AdapterFavorite;
import com.example.movietrailer.adapter.movie.AdapterCast;
import com.example.movietrailer.adapter.movie.AdapterGenres;
import com.example.movietrailer.adapter.movie.AdapterReview;
import com.example.movietrailer.adapter.movie.AdapterSlideMovieDetail;
import com.example.movietrailer.adapter.movie.AdapterVideo;
import com.example.movietrailer.adapter.movie.AdpaterCountry;
import com.example.movietrailer.model.ConnectServer;
import com.example.movietrailer.model.movie.ApiResponseMovie;
import com.example.movietrailer.model.movie.ApiResponseMovieCast;
import com.example.movietrailer.model.movie.ApiResponseMovieDetail;
import com.example.movietrailer.model.movie.ApiResponseMovieImages;
import com.example.movietrailer.model.movie.ApiResponseMovieReview;
import com.example.movietrailer.model.movie.ApiResponseMovieVideo;
import com.example.movietrailer.model.movie.CategoryMovie;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetail extends AppCompatActivity {
    private ArrayList<CategoryMovie> listCategory = new ArrayList<>();
    private ArrayList<ApiResponseMovie.Movie> listMovieSimilar = new ArrayList<>();
    private ArrayList<ApiResponseMovie.Movie> listMovieRecommend = new ArrayList<>();
    private ArrayList<ApiResponseMovieDetail.Genre> listGenre = new ArrayList<>();
    private ArrayList<ApiResponseMovieCast.Cast> listCast = new ArrayList<>();
    private ArrayList<ApiResponseMovieCast.Crew> listCrew = new ArrayList<>();
    private ArrayList<ApiResponseMovieImages.Backdrop> listSLide = new ArrayList<>();
    private ArrayList<ApiResponseMovieImages.Backdrop> mlistSLide = new ArrayList<>();
    private ArrayList<ApiResponseMovieReview.ReviewMovie> listReview = new ArrayList<>();
    private ArrayList<ApiResponseMovieVideo.MovieVideo> listVideo = new ArrayList<>();
    private ArrayList<ApiResponseMovieDetail.ProductionCountry> listCountry = new ArrayList<>();
    private ArrayList<Integer> listReviewId = new ArrayList<Integer>();
    private AdapterCategoryMovie adapterCategoryMovie;
    private AdapterCast adapterCast;
    private AdapterGenres adapterGenres;
    private AdapterReview adapterReview;
    private AdpaterCountry adpaterCountry;
    private AdapterSlideMovieDetail adapterSlideMovieDetail;
    private AdapterVideo adapterVideo;
    private RecyclerView re_category, rcv_genres, rcv_cast, rcv_review, rcv_country, rcv_video;
    private ViewPager viewPager;
    private TextView tvTitle, tvRelease, tvDuration, tvDirector, tvImdb, tvOverview, tvViewMore, tvVote, tvCountReview;
    private ImageView ivPoster, ivTrailer, ivPlay, ivFavourite, ivReview;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private int idMovie;
    private Detail movie;
    private String favTitle, favRelease, favPoster;
    private Double favVote;
    private boolean isExpanded = true;
    private FloatingActionButton floatingActionButton;
    private Menu mMenu;
    private Timer timer;
    private MovieViewModel movieViewModel;
    private MovieDatabase movieDatabase;
    private String category = "Movie";
    private MenuItem favItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        movieViewModel = new ViewModelProviders().of(this).get(MovieViewModel.class);
        initData();
        initView();
        initGenre();
        initCast();
        initReview();
        initCountry();
        initSlide();
        autoSlide();
        callApiVideo();
        initToolbar();
        initVideo();
        initShowRecyclerView();
        initToolbarAnimation();
        initFavorite();
        callApigetDirector();
        movieDatabase = Room.databaseBuilder(getApplicationContext(), MovieDatabase.class, "table_demo").build();
        toolbar = findViewById(R.id.toolbar_movie_detail);
        favItem = findViewById(R.id.toobar_fav);
        floatingActionButton = findViewById(R.id.fab_favorite);
        setSupportActionBar(toolbar);


    }

    public void initData() {
        Intent intent = getIntent();
        idMovie = intent.getIntExtra(Constants.KEY_ID_MOVIE, -1);
    }

    private void initView() {
        tvTitle = findViewById(R.id.tv_title);
        tvDirector = findViewById(R.id.tv_direction);
        tvImdb = findViewById(R.id.tv_imdb);
        tvOverview = findViewById(R.id.tv_overview);
        tvRelease = findViewById(R.id.tv_release);
        tvDuration = findViewById(R.id.tv_duration);
        tvViewMore = findViewById(R.id.tv_view_more);
        tvVote = findViewById(R.id.tv_vote);
        tvCountReview = findViewById(R.id.tv_count_review);
        ivPoster = findViewById(R.id.iv_poster_movie_detail);
        ivPlay = findViewById(R.id.iv_play);
        ivTrailer = findViewById(R.id.iv_trailer);
        ivReview = findViewById(R.id.iv_reviewer);
        viewPager = findViewById(R.id.iv_slide_detail);
        rcv_genres = findViewById(R.id.rvc_genres);
        rcv_cast = findViewById(R.id.rcv_cast);
        rcv_review = findViewById(R.id.rcv_review);
        rcv_country = findViewById(R.id.rcv_country);
        rcv_video = findViewById(R.id.rcv_trailer);
        appBarLayout = findViewById(R.id.appbar);
        collapsingToolbarLayout = findViewById(R.id.collap_detail_movie);
        toolbar = findViewById(R.id.toolbar_movie_detail);
        favItem = findViewById(R.id.toobar_fav);
        floatingActionButton = findViewById(R.id.fab_favorite);
        floatingActionButton.setImageResource(movieViewModel.isFavorite(idMovie) ? R.drawable.ic_favorite_red : R.drawable.ic_love_white);

    }

    private void initGenre() {
        rcv_genres.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        adapterGenres = new AdapterGenres(MovieDetail.this, listGenre);
        rcv_genres.setAdapter(adapterGenres);
        callApiMovieGenre();
    }

    private void initCast() {
        rcv_cast.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        adapterCast = new AdapterCast(MovieDetail.this, listCast);
        rcv_cast.setAdapter(adapterCast);
        callApiMovieCast();
    }

    private void initReview() {
        rcv_review.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapterReview = new AdapterReview(MovieDetail.this, listReview);
        adapterReview.getIdReviewMovie(idMovie);
        rcv_review.setAdapter(adapterReview);
        callApiMovieReview();
    }

    private void initCountry() {
        rcv_country.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        adpaterCountry = new AdpaterCountry(MovieDetail.this, listCountry);
        rcv_country.setAdapter(adpaterCountry);
        callApiMovieCountry();
    }

    private void initSlide() {
        adapterSlideMovieDetail = new AdapterSlideMovieDetail(MovieDetail.this, mlistSLide);
        viewPager.setAdapter(adapterSlideMovieDetail);
        callApiImageSlide();

    }

    private void autoSlide() {
        if (mlistSLide == null || mlistSLide.isEmpty() || viewPager == null) {
            return;
        }
        //init timer
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = mlistSLide.size() - 1;
                        if (currentItem < totalItem) {
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 300, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void initVideo() {
        rcv_video.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        adapterVideo = new AdapterVideo(MovieDetail.this, listVideo, new AdapterVideo.OnClickItemVideo() {
            @Override
            public void onClick(int position) {
                String key = "";
                key = listVideo.get(position).getKey();
                Intent intent = new Intent(MovieDetail.this, Video.class);
                intent.putExtra(Constants.KEY_YOUTUBE, key);
                startActivity(intent);
            }
        });
        rcv_video.setAdapter(adapterVideo);

    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initToolbarAnimation() {
        collapsingToolbarLayout.setExpandedTitleMarginStart(420);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.color_selected_bottom_nav));
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.black_trans));
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) > 100) {
                    isExpanded = false;
                } else {
                    isExpanded = true;
                }
                invalidateOptionsMenu();
            }
        });

    }
//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        mMenu = menu;
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // nếu menu khác null và biến isexpaned = false, và size menu #1
        if (mMenu != null && (!isExpanded || mMenu.size() != 2)) {
            //collapse
            if (movieViewModel.isFavorite(idMovie)){
                mMenu.add("Favorite").setIcon(R.drawable.ic_favorite_red).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            }
            else
            {
                mMenu.add("Favorite").setIcon(R.drawable.ic_love_white).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            }

//            mMenu.add("Share").setIcon(R.drawable.ic_share).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        } else {
            //expaned
        }
        return super.onPrepareOptionsMenu(mMenu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        movie = new Detail(idMovie, favPoster, favRelease, favTitle, favVote, "Movie","", "");
        if("Favorite".equals(item.getTitle())){
            if (movieViewModel.isFavorite(idMovie)) {
                floatingActionButton.setImageResource(R.drawable.ic_love_white);
                movieViewModel.RemoveFavList(movie);
                Toast.makeText(MovieDetail.this, "Movie is deleted from Favorite list", Toast.LENGTH_SHORT).show();
//                    isFavorite = 1;
            } else {
                Intent intent = new Intent(MovieDetail.this, AdapterFavorite.class);
                floatingActionButton.setImageResource(R.drawable.ic_favorite_red);
                movieViewModel.addFavList(movie);
                Toast.makeText(MovieDetail.this, "Movie is added to Favorite list", Toast.LENGTH_SHORT).show();
//                    isFavorite = 0;
            }
        }
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void initFavorite() {
        movie = new Detail(idMovie, favPoster, favRelease, favTitle, favVote,"Movie", "", "");
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movieViewModel.isFavorite(idMovie)) {
                    floatingActionButton.setImageResource(R.drawable.ic_love_white);
                    movieViewModel.RemoveFavList(movie);
                    Toast.makeText(MovieDetail.this, "Movie is deleted from  Favorite list", Toast.LENGTH_SHORT).show();
                } else {
//                    Intent intent = new Intent(MovieDetail.this, AdapterFavorite.class);
                    floatingActionButton.setImageResource(R.drawable.ic_favorite_red);
                    movieViewModel.addFavList(movie);
                    Toast.makeText(MovieDetail.this, "Movie is added to Favorite list", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void initEventsFav(){
        favItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
    }


    private void callApiImageSlide() {
        ConnectServer.getApiService().getListMovieImages(idMovie).enqueue(new Callback<ApiResponseMovieImages>() {
            @Override
            public void onResponse(Call<ApiResponseMovieImages> call, Response<ApiResponseMovieImages> response) {
                if (response.isSuccessful()) {
                    ApiResponseMovieImages apiResponseMovieImages = response.body();
                    mlistSLide.addAll(apiResponseMovieImages.getBackdrops());
                    adapterSlideMovieDetail.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseMovieImages> call, Throwable t) {
                Log.e("kevin", "onFailure: " + t.toString());
            }
        });
    }


    private void callApiVideo() {
        ConnectServer.getApiService().getListMovieVideo(idMovie).enqueue(new Callback<ApiResponseMovieVideo>() {

            @Override
            public void onResponse(Call<ApiResponseMovieVideo> call, Response<ApiResponseMovieVideo> response) {
                if (response.isSuccessful()) {
                    ApiResponseMovieVideo listvideo = response.body();
                    listVideo.addAll(listvideo.getResults());
                    adapterVideo.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseMovieVideo> call, Throwable t) {
                Log.e("kevin", "onFailure: " + t.toString());
            }
        });
    }


    private void callApiMovieCountry() {
        ConnectServer.getApiService().getDetailMovie(idMovie).enqueue(new Callback<ApiResponseMovieDetail>() {
            @Override
            public void onResponse(Call<ApiResponseMovieDetail> call, Response<ApiResponseMovieDetail> response) {
                if (response.isSuccessful()) {
                    ApiResponseMovieDetail apiResponseMovieDetail = response.body();
                    listCountry.addAll(apiResponseMovieDetail.getProductionCountries());
                    adpaterCountry.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseMovieDetail> call, Throwable t) {

            }
        });
    }

    private void callApiMovieReview() {
        ConnectServer.getApiService().getListMovieReview(idMovie).enqueue(new Callback<ApiResponseMovieReview>() {
            @Override
            public void onResponse(Call<ApiResponseMovieReview> call, Response<ApiResponseMovieReview> response) {
                if (response.isSuccessful()) {
                    ApiResponseMovieReview apiResponseMovieReview = response.body();
                    listReview.addAll(apiResponseMovieReview.getResults());
                    if (listReview.size() > 1) {
                        tvCountReview.setText(Integer.toString(listReview.size()) + " Reviews");
                    } else {
                        tvCountReview.setText(Integer.toString(listReview.size()) + " Review");
                    }

                    adapterReview.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseMovieReview> call, Throwable t) {

            }
        });
    }

    private void callApiMovieGenre() {
        ConnectServer.getApiService().getDetailMovie(idMovie).enqueue(new Callback<ApiResponseMovieDetail>() {
            @Override
            public void onResponse(Call<ApiResponseMovieDetail> call, Response<ApiResponseMovieDetail> response) {
                if (response.isSuccessful()) {
                    ApiResponseMovieDetail apiResponseMovieDetail = response.body();
                    listGenre.addAll(apiResponseMovieDetail.getGenres());
                    adapterGenres.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseMovieDetail> call, Throwable t) {

            }
        });
    }

    private void callApiMovieCast() {
        ConnectServer.getApiService().getListMovieCast(idMovie).enqueue(new Callback<ApiResponseMovieCast>() {
            @Override
            public void onResponse(Call<ApiResponseMovieCast> call, Response<ApiResponseMovieCast> response) {
                if (response.isSuccessful()) {
                    ApiResponseMovieCast cast = response.body();
                    listCast.addAll(cast.getCast());
                    adapterCast.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseMovieCast> call, Throwable t) {

            }
        });
    }

    private void initShowRecyclerView() {
        re_category = findViewById(R.id.re_category);
        adapterCategoryMovie = new AdapterCategoryMovie(MovieDetail.this, listCategory);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MovieDetail.this, RecyclerView.VERTICAL, false);
        re_category.setLayoutManager(linearLayoutManager);
        re_category.setAdapter(adapterCategoryMovie);
        callApiMovieCategory();
        callApiMovieSimilar();
        callApiMoiveRecommend();
    }

    private void callApiMovieCategory() {
        ConnectServer.getApiService().getDetailMovie(idMovie).enqueue(new Callback<ApiResponseMovieDetail>() {
            @Override
            public void onResponse(Call<ApiResponseMovieDetail> call, Response<ApiResponseMovieDetail> response) {
                if (response.isSuccessful()) {
                    ApiResponseMovieDetail apiResponseMovieDetail = response.body();
                    collapsingToolbarLayout.setTitle(apiResponseMovieDetail.getTitle());
                    tvDuration.setText(Integer.toString(apiResponseMovieDetail.getRuntime()));
                    tvOverview.setText(apiResponseMovieDetail.getOverview());
                    tvImdb.setText(Double.toString(apiResponseMovieDetail.getVoteAverage()));
                    tvVote.setText(Integer.toString(apiResponseMovieDetail.getVoteCount()));
                    tvRelease.setText(apiResponseMovieDetail.getReleaseDate());
                    favVote = apiResponseMovieDetail.getVoteAverage();
                    favPoster = apiResponseMovieDetail.getPosterPath();
                    favRelease = apiResponseMovieDetail.getReleaseDate();
                    Glide.with(MovieDetail.this).load(Constants.KEY_BASE_IMAGE_URL + apiResponseMovieDetail.getPosterPath()).into(ivPoster);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseMovieDetail> call, Throwable t) {
            }
        });
    }

    private void callApigetDirector() {
        ConnectServer.getApiService().getListMovieCast(idMovie).enqueue(new Callback<ApiResponseMovieCast>() {
            @Override
            public void onResponse(Call<ApiResponseMovieCast> call, Response<ApiResponseMovieCast> response) {
                if (response.isSuccessful()) {
                    ApiResponseMovieCast crew = response.body();
                    listCrew.addAll(crew.getCrew());
                    for (ApiResponseMovieCast.Crew i : listCrew) {
                        if (i.getJob().equals("Director")) {
                            tvDirector.setText(i.getName());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponseMovieCast> call, Throwable t) {

            }
        });
    }

    private void callApiMovieSimilar() {

        ConnectServer.getApiService().getListMovieSimilar(idMovie).enqueue(new Callback<ApiResponseMovie>() {
            @Override
            public void onResponse(Call<ApiResponseMovie> call, Response<ApiResponseMovie> response) {
                if (response.isSuccessful()) {
                    ApiResponseMovie apiResponseMovie = response.body();
                    listMovieSimilar.addAll(apiResponseMovie.getResults());
                    listCategory.add(new CategoryMovie("Similar", listMovieSimilar));
                    adapterCategoryMovie.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<ApiResponseMovie> call, Throwable t) {

            }
        });
    }

    private void callApiMoiveRecommend() {
        ConnectServer.getApiService().getListMovieRecommend(idMovie).enqueue(new Callback<ApiResponseMovie>() {
            @Override
            public void onResponse(Call<ApiResponseMovie> call, Response<ApiResponseMovie> response) {
                if (response.isSuccessful()) {
                    ApiResponseMovie apiResponseMovie = response.body();
                    listMovieRecommend.addAll(apiResponseMovie.getResults());
                    listCategory.add(new CategoryMovie("Recommendations", listMovieRecommend));
                    adapterCategoryMovie.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseMovie> call, Throwable t) {

            }
        });
    }


}
