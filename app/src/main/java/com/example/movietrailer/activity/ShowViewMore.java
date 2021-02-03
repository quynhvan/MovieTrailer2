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
import com.example.movietrailer.adapter.show.AdapterViewMoreShow;
import com.example.movietrailer.model.movie.ApiResponseMovie;
import com.example.movietrailer.R;
import com.example.movietrailer.model.show.ApiResponseShow;

import java.util.ArrayList;

public class ShowViewMore extends AppCompatActivity {
    private RecyclerView rcvMoive;
    private TextView tvCategory;
    private ArrayList<ApiResponseShow.Show> listMovie = new ArrayList<>();
    private AdapterViewMoreShow adapterViewMoreShow;
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
        rcvMoive.setLayoutManager(new GridLayoutManager(ShowViewMore.this,3));
        adapterViewMoreShow = new AdapterViewMoreShow(ShowViewMore.this, listMovie, new AdapterViewMoreShow.OnClickItemInViewMore() {
            @Override
            public void onClick(int position) {
                ApiResponseShow.Show movie = listMovie.get(position);
                Intent intent = new Intent(ShowViewMore.this, ShowDetail.class);
                intent.putExtra(Constants.KEY_ID_SHOW,movie.getId());
                startActivity(intent);
            }
        });
        rcvMoive.setAdapter(adapterViewMoreShow);
        adapterViewMoreShow.notifyDataSetChanged();

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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_view_more,menu);
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.setMaxWidth(200);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapterViewMore.getFilter().filter(newText);
//                return false;
//            }
//        });
//        return true;
//    }
}
