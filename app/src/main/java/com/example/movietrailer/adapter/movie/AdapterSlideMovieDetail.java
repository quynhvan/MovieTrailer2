package com.example.movietrailer.adapter.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.movietrailer.Constants;
import com.example.movietrailer.model.movie.ApiResponseMovieImages;
import com.example.movietrailer.R;
import com.flaviofaria.kenburnsview.KenBurnsView;

import java.util.ArrayList;

public class AdapterSlideMovieDetail extends PagerAdapter {
    private Context context;
    private ArrayList<ApiResponseMovieImages.Backdrop> listSLide = new ArrayList<>();
    private LayoutInflater inflater;

    public AdapterSlideMovieDetail(Context context, ArrayList<ApiResponseMovieImages.Backdrop> listSLide) {
        this.context = context;
        this.listSLide = listSLide;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_kenburnview,container,false);
        KenBurnsView ivAnh = view.findViewById(R.id.iv_kenburnview);

        ApiResponseMovieImages.Backdrop backDrop = listSLide.get(position);
        Glide.with(context).load(Constants.KEY_BASE_IMAGE_URL+backDrop.getFilePath()).into(ivAnh);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return listSLide.size();
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
