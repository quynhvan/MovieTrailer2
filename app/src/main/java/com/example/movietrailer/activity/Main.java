package com.example.movietrailer.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.movietrailer.adapter.ViewPagerAdapter;
import com.example.movietrailer.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private TextView tvViewMore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);
        viewPager = findViewById(R.id.view_pager);
        navigationView = findViewById(R.id.bottom_tablayout);
        setUpViewPager();
        // xu ly su kien khi click vao bottom navigation
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_movie:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.action_show:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.action_favorite:
                        viewPager.setCurrentItem(2);
                        break;
//                    case R.id.action_watchtlist:
//                        viewPager.setCurrentItem(3);
//                        break;
                }
                return true;
            }
        });
    }
    private void setUpViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);

        // xu ly su kien khi dung tay vuot trang
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        navigationView.getMenu().findItem(R.id.action_movie).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.action_show).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.action_favorite).setChecked(true);
                        break;
//                    case 3:
//                        navigationView.getMenu().findItem(R.id.action_watchtlist).setChecked(true);
//                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
