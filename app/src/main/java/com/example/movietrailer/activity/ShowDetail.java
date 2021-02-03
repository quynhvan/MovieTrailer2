package com.example.movietrailer.activity;

import android.content.Intent;
import android.os.Bundle;
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
import androidx.core.widget.NestedScrollView;
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
import com.example.movietrailer.adapter.AdapterFavorite;
import com.example.movietrailer.adapter.movie.AdapterCast;
import com.example.movietrailer.adapter.movie.AdapterReview;
import com.example.movietrailer.adapter.movie.AdapterSlideMovieDetail;
import com.example.movietrailer.adapter.movie.AdapterVideo;
import com.example.movietrailer.adapter.show.AdapterShowCategory;
import com.example.movietrailer.adapter.show.AdapterShowCountry;
import com.example.movietrailer.adapter.show.AdapterShowGenre;
import com.example.movietrailer.model.ConnectServer;
import com.example.movietrailer.model.movie.ApiResponseMovieCast;
import com.example.movietrailer.model.movie.ApiResponseMovieImages;
import com.example.movietrailer.model.movie.ApiResponseMovieReview;
import com.example.movietrailer.model.movie.ApiResponseMovieVideo;
import com.example.movietrailer.model.show.ApiResponseShow;
import com.example.movietrailer.model.show.ApiResponseShowDetail;
import com.example.movietrailer.model.show.CategoryShow;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowDetail extends AppCompatActivity {
    private ArrayList<CategoryShow> listCategory = new ArrayList<>();
    private ArrayList<ApiResponseShow.Show> listMovieSimilar = new ArrayList<>();
    private ArrayList<ApiResponseShow.Show> listMovieRecommend = new ArrayList<>();
    private ArrayList<ApiResponseShowDetail.Genres> listGenre = new ArrayList<>();
    private ArrayList<ApiResponseMovieCast.Cast> listCast = new ArrayList<>();
    private ArrayList<ApiResponseMovieCast.Crew> listCrew = new ArrayList<>();
    private ArrayList<ApiResponseMovieImages.Backdrop> listSLide = new ArrayList<>();
    private ArrayList<ApiResponseMovieReview.ReviewMovie> listReview = new ArrayList<>();
    private ArrayList<ApiResponseMovieVideo.MovieVideo> listVideo = new ArrayList<>();
    private ArrayList<ApiResponseShowDetail.Networks> listCountry = new ArrayList<>();
    private AdapterShowCategory adapterShowCategory;
    private AdapterCast adapterCast;
    private AdapterReview adapterReview;
    private AdapterShowCountry adapterShowCountry;
    private AdapterShowGenre adapterShowGenre;
    private AdapterSlideMovieDetail adapterSlideMovieDetail;
    private AdapterVideo adapterVideo;
    private RecyclerView re_category, rcv_genres, rcv_cast, rcv_review, rcv_country,rcv_video;
    private Integer idMovie;
    private int idReview;
    private ViewPager viewPager;
    private TextView tvTitle, tvRelease, tvDuration, tvDirector, tvImdb, tvOverview, tvViewMore, tvVote, tvCountReview;
    private ImageView ivPoster, ivTrailer, ivPlay, ivFavourite;
    private String key;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private FloatingActionButton floatingActionButton;
    private boolean isExpanded = true;
    private Menu mMenu;
    private NestedScrollView nestedScrollView;
    private Detail show;
    private String favTitle, favRelease, favPoster;
    private Double favVote;
    private MovieViewModel movieViewModel;
    private String category = "TV";
    private MovieDatabase movieDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        movieViewModel = new ViewModelProviders().of(this).get(MovieViewModel.class);
        movieDatabase = Room.databaseBuilder(getApplicationContext(), MovieDatabase.class, "table_demo").build();
        initData();
        initView();
        initGenre();
        initCast();
        initReview();
        initCountry();
        initSlide();
        initVideo();
        initToolbar();
        initToolbarAnimation();
        callApiVideo();
        initShowRecyclerView();
        initFavorite();
        callApigetDirector();


    }

    public void initData() {
        Intent intent = getIntent();
        idMovie = intent.getIntExtra(Constants.KEY_ID_SHOW, -1);

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
        viewPager = findViewById(R.id.iv_slide_detail);
        rcv_genres = findViewById(R.id.rvc_genres);
        rcv_cast = findViewById(R.id.rcv_cast);
        rcv_review = findViewById(R.id.rcv_review);
        rcv_country = findViewById(R.id.rcv_country);
        rcv_video = findViewById(R.id.rcv_trailer);
        appBarLayout = findViewById(R.id.appbar);
        collapsingToolbarLayout = findViewById(R.id.collap_detail_movie);
        toolbar = findViewById(R.id.toolbar_movie_detail);
        floatingActionButton = findViewById(R.id.fab_favorite);
        nestedScrollView = findViewById(R.id.nested_scroll);
        floatingActionButton.setImageResource(movieViewModel.isFavorite(idMovie) ? R.drawable.ic_favorite_red : R.drawable.ic_love_white);
    }

    private void initGenre() {
        rcv_genres.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        adapterShowGenre = new AdapterShowGenre(ShowDetail.this, listGenre);
        rcv_genres.setAdapter(adapterShowGenre);
        callApiMovieGenre();
    }

    private void initCast() {
        rcv_cast.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        adapterCast = new AdapterCast(ShowDetail.this, listCast);
        rcv_cast.setAdapter(adapterCast);
        callApiMovieCast();
    }

    private void initReview() {
        rcv_review.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapterReview = new AdapterReview(ShowDetail.this, listReview);
        adapterReview.getIdTvReview(idMovie);
        rcv_review.setAdapter(adapterReview);
        callApiShowReview();
    }

    private void initCountry() {
        rcv_country.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        adapterShowCountry = new AdapterShowCountry(ShowDetail.this, listCountry);
        rcv_country.setAdapter(adapterShowCountry);
        callApiMovieCountry();
    }

    private void initSlide() {
        adapterSlideMovieDetail = new AdapterSlideMovieDetail(ShowDetail.this, listSLide);
        viewPager.setAdapter(adapterSlideMovieDetail);
        callApiImageSlide();

    }

    private void initVideo() {
        rcv_video.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        adapterVideo = new AdapterVideo(ShowDetail.this, listVideo, new AdapterVideo.OnClickItemVideo() {
            @Override
            public void onClick(int position) {
                String key = "";
                key = listVideo.get(position).getKey();
                Intent intent = new Intent(ShowDetail.this, Video.class);
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
            // để hiện button mũi tên trỏ trái
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
        show = new Detail(idMovie, favPoster, " ", " ", favVote,"TV", favTitle,favRelease);
        if("Favorite".equals(item.getTitle())){
            if (movieViewModel.isFavorite(idMovie)) {
                floatingActionButton.setImageResource(R.drawable.ic_love_white);
                movieViewModel.RemoveFavList(show);
                Toast.makeText(ShowDetail.this, "Movie is deleted from Favorite list", Toast.LENGTH_SHORT).show();
//                    isFavorite = 1;
            } else {
                Intent intent = new Intent(ShowDetail.this, AdapterFavorite.class);
                floatingActionButton.setImageResource(R.drawable.ic_favorite_red);
                movieViewModel.addFavList(show);
                Toast.makeText(ShowDetail.this, "Movie is added to Favorite list", Toast.LENGTH_SHORT).show();
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
        show = new Detail(idMovie, favPoster, " ", " ", favVote,"TV", favTitle,favRelease);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movieViewModel.isFavorite(idMovie)) {
                    floatingActionButton.setImageResource(R.drawable.ic_love_white);
                    movieViewModel.RemoveFavList(show);
                    Toast.makeText(ShowDetail.this, "Movie is deleted from Favorite list", Toast.LENGTH_SHORT).show();
//                    isFavorite = 1;
                } else {
                    floatingActionButton.setImageResource(R.drawable.ic_favorite_red);
                    movieViewModel.addFavList(show);
                    Toast.makeText(ShowDetail.this, "Movie is added to Favorite list", Toast.LENGTH_SHORT).show();
//                    isFavorite = 0;
                }
            }
        });
    }



    private void callApiImageSlide() {
        ConnectServer.getApiService().getListShowImage(idMovie).enqueue(new Callback<ApiResponseMovieImages>() {
            @Override
            public void onResponse(Call<ApiResponseMovieImages> call, Response<ApiResponseMovieImages> response) {
                if (response.isSuccessful()) {
                    ApiResponseMovieImages apiResponseMovieImages = response.body();
                    listSLide.addAll(apiResponseMovieImages.getBackdrops());
                    adapterSlideMovieDetail.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseMovieImages> call, Throwable t) {
            }
        });
    }

    private void callApiVideo() {
        ConnectServer.getApiService().getListShowVideo(idMovie).enqueue(new Callback<ApiResponseMovieVideo>() {

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

            }
        });
    }

    private void callApiMovieCountry() {
        ConnectServer.getApiService().getDetailShow(idMovie).enqueue(new Callback<ApiResponseShowDetail>() {
            @Override
            public void onResponse(Call<ApiResponseShowDetail> call, Response<ApiResponseShowDetail> response) {
                if (response.isSuccessful()) {
                    ApiResponseShowDetail apiResponseShowDetail = response.body();
                    listCountry.addAll(apiResponseShowDetail.getNetworks());
                    adapterShowCountry.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseShowDetail> call, Throwable t) {

            }
        });
    }

    private void callApiShowReview() {
        ConnectServer.getApiService().getListShowReview(idMovie).enqueue(new Callback<ApiResponseMovieReview>() {
            @Override
            public void onResponse(Call<ApiResponseMovieReview> call, Response<ApiResponseMovieReview> response) {
                if (response.isSuccessful()) {
                    ApiResponseMovieReview apiResponseShowReview = response.body();
                    listReview.addAll(apiResponseShowReview.getResults());
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
        ConnectServer.getApiService().getDetailShow(idMovie).enqueue(new Callback<ApiResponseShowDetail>() {
            @Override
            public void onResponse(Call<ApiResponseShowDetail> call, Response<ApiResponseShowDetail> response) {
                if (response.isSuccessful()) {
                    ApiResponseShowDetail apiResponseShowDetail = response.body();
                    listGenre.addAll(apiResponseShowDetail.getGenres());
                    adapterShowGenre.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseShowDetail> call, Throwable t) {

            }
        });
    }

    private void callApiMovieCast() {
        ConnectServer.getApiService().getListShowCast(idMovie).enqueue(new Callback<ApiResponseMovieCast>() {
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
        adapterShowCategory = new AdapterShowCategory(ShowDetail.this, listCategory);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShowDetail.this, RecyclerView.VERTICAL, false);
        re_category.setLayoutManager(linearLayoutManager);
        re_category.setAdapter(adapterShowCategory);
        callApiShowCategory();
        callApiShowSimilar();
    }

    private void callApiShowCategory() {
        ConnectServer.getApiService().getDetailShow(idMovie).enqueue(new Callback<ApiResponseShowDetail>() {
            @Override
            public void onResponse(Call<ApiResponseShowDetail> call, Response<ApiResponseShowDetail> response) {
                if (response.isSuccessful()) {
                    ApiResponseShowDetail apiResponseShowDetail = response.body();
                    collapsingToolbarLayout.setTitle(apiResponseShowDetail.getName());
                    favTitle = apiResponseShowDetail.getName();
                    favPoster = apiResponseShowDetail.getPosterPath();
                    favRelease= apiResponseShowDetail.getFirstAirDate();
                    favVote = apiResponseShowDetail.getVoteAverage();
                    tvOverview.setText(apiResponseShowDetail.getOverview());
                    tvVote.setText(Integer.toString(apiResponseShowDetail.getVoteCount()));
                    tvRelease.setText(apiResponseShowDetail.getFirstAirDate());
//                    tvDuration.setText("null");
                    tvImdb.setText(Double.toString(apiResponseShowDetail.getVoteAverage()));
                    Glide.with(ShowDetail.this).load(Constants.KEY_BASE_IMAGE_URL + apiResponseShowDetail.getPosterPath()).into(ivPoster);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseShowDetail> call, Throwable t) {
            }
        });
    }

    private void callApigetDirector() {
        ConnectServer.getApiService().getListShowCast(idMovie).enqueue(new Callback<ApiResponseMovieCast>() {
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

    private void callApiShowSimilar() {

        ConnectServer.getApiService().getListShowSimilar(idMovie).enqueue(new Callback<ApiResponseShow>() {
            @Override
            public void onResponse(Call<ApiResponseShow> call, Response<ApiResponseShow> response) {
                if (response.isSuccessful()) {
                    ApiResponseShow apiResponseShow = response.body();
                    listMovieSimilar.addAll(apiResponseShow.getResults());
                    listCategory.add(new CategoryShow("Similar", listMovieSimilar));
                    adapterShowCategory.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<ApiResponseShow> call, Throwable t) {
            }
        });
    }


}
