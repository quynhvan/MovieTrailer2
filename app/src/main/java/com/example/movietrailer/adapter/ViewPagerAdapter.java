package com.example.movietrailer.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.movietrailer.activity.FavoriteFragment;
import com.example.movietrailer.activity.MovieActivity;
import com.example.movietrailer.activity.ShowActivity;
import com.example.movietrailer.activity.WatchList;

public class ViewPagerAdapter  extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MovieActivity();
            case 1:
                return new ShowActivity();
            default:
                return new FavoriteFragment();
        }
    }
    @Override
    public int getCount() {
        return 3;
    }
}
