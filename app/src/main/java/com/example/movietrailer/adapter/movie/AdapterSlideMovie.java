package com.example.movietrailer.adapter.movie;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.movietrailer.Constants;
import com.example.movietrailer.activity.MovieDetail;
import com.example.movietrailer.model.movie.ApiResponseMovie;
import com.example.movietrailer.R;

import java.util.ArrayList;

public class AdapterSlideMovie extends PagerAdapter {
    private Context context;
    private ArrayList<ApiResponseMovie.Movie> listMovie = new ArrayList<>();
    private LayoutInflater inflater;

    public AdapterSlideMovie(Context context,  ArrayList<ApiResponseMovie.Movie> listMovie) {
        this.context = context;
        this.listMovie = listMovie;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = inflater.inflate(R.layout.item_fragment_movie_slide,container,false);

        ImageView ivAnh = view.findViewById(R.id.iv_slide);
        TextView tvTitle = view.findViewById(R.id.tv_title);
        TextView tvRate = view.findViewById(R.id.tv_rate);

        ApiResponseMovie.Movie slide = listMovie.get(position);
        Glide.with(context).load(Constants.KEY_BASE_IMAGE_URL+slide.getBackdropPath()).into(ivAnh);
        tvTitle.setText(slide.getTitle());
        tvRate.setText(Double.toString(slide.getVoteAverage()));

        container.addView(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetail.class);
                intent.putExtra(Constants.KEY_ID_MOVIE, slide.getId());
                view.getContext().startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public int getCount() {
        return listMovie.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==  object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // remove view
        container.removeView((View) object);
    }
}
