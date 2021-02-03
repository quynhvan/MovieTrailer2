package com.example.movietrailer.adapter.show;

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
import com.example.movietrailer.R;
import com.example.movietrailer.activity.MovieDetail;
import com.example.movietrailer.activity.ShowDetail;
import com.example.movietrailer.model.movie.ApiResponseMovie;
import com.example.movietrailer.model.show.ApiResponseShow;

import java.util.ArrayList;

public class AdapterShowSlide extends PagerAdapter {
    private Context context;
    private ArrayList<ApiResponseShow.Show> listSlide = new ArrayList<>();
    private LayoutInflater inflater;
    public AdapterShowSlide(Context context,ArrayList<ApiResponseShow.Show> listSlide) {
        this.context = context;
        this.listSlide = listSlide;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listSlide.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = inflater.inflate(R.layout.item_fragment_movie_slide,container,false);

        ImageView ivAnh = view.findViewById(R.id.iv_slide);
        TextView tvTitle = view.findViewById(R.id.tv_title);
        TextView tvRate = view.findViewById(R.id.tv_rate);

        ApiResponseShow.Show slide = listSlide.get(position);

        Glide.with(context).load(Constants.KEY_BASE_IMAGE_URL+slide.getBackdropPath()).into(ivAnh);
        tvTitle.setText(slide.getTitle());
        tvRate.setText(Double.toString(slide.getVoteAverage()));
        container.addView(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowDetail.class);
                intent.putExtra(Constants.KEY_ID_MOVIE, slide.getId());
                view.getContext().startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}
