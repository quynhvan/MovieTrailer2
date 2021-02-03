package com.example.movietrailer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movietrailer.adapter.movie.AdapterViewMore;
import com.example.movietrailer.Constants;
import com.example.movietrailer.model.movie.ApiResponseMovie;
import com.example.movietrailer.R;

import java.util.ArrayList;

public class MovieViewMore extends AppCompatActivity {
    private RecyclerView rcvMoive;
    private TextView tvCategory;
    private ArrayList<ApiResponseMovie.Movie> listMovie = new ArrayList<>();
    private AdapterViewMore adapterViewMore;
    private String nameCategory;
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_view_more);
        toolbar= findViewById(R.id.toolbar_viewmore);
        initData();
        initShow();
        initToolbar();
    }
    private void initData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        rcvMoive = findViewById(R.id.rcv_movie);
        tvCategory = findViewById(R.id.tv_category);
        listMovie = bundle.getParcelableArrayList(Constants.KEY_VIEW_MORE_MOVIE);
        nameCategory = bundle.getString(Constants.KEY_NAME_CATEGORY,"");

    }
    private void initShow(){
        tvCategory.setText(nameCategory);
        rcvMoive.setLayoutManager(new GridLayoutManager(MovieViewMore.this,3));
        adapterViewMore = new AdapterViewMore(MovieViewMore.this, listMovie, new AdapterViewMore.OnClickItemInViewMore() {
            @Override
            public void onClick(int position) {
                ApiResponseMovie.Movie movie = listMovie.get(position);
                Intent intent = new Intent(MovieViewMore.this, MovieDetail.class);
                intent.putExtra(Constants.KEY_ID_MOVIE,movie.getId());
                startActivity(intent);
            }
        });
        rcvMoive.setAdapter(adapterViewMore);
        adapterViewMore.notifyDataSetChanged();

    }
    private void initToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
