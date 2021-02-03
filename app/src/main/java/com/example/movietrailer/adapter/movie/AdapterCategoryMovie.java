package com.example.movietrailer.adapter.movie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movietrailer.activity.MovieViewMore;
import com.example.movietrailer.Constants;
import com.example.movietrailer.model.movie.CategoryMovie;
import com.example.movietrailer.R;

import java.util.ArrayList;

public class AdapterCategoryMovie extends RecyclerView.Adapter<AdapterCategoryMovie.MyViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<CategoryMovie> listCategory = new ArrayList<>();

    public AdapterCategoryMovie(Context context, ArrayList<CategoryMovie> listCategory) {
        this.context = context;
        this.listCategory = listCategory;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AdapterCategoryMovie.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_category, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCategoryMovie.MyViewHolder holder, int position) {
        CategoryMovie categoryMovie = listCategory.get(position);
        holder.tvCategory.setText(categoryMovie.getNameCategory());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        holder.rcMovie.setLayoutManager(linearLayoutManager);

        AdapterMovieItem adapterMovieItem = new AdapterMovieItem(context);
        adapterMovieItem.setData(categoryMovie.getListMovie());
        holder.rcMovie.setAdapter(adapterMovieItem);

        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tvViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieViewMore.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(Constants.KEY_VIEW_MORE_MOVIE, categoryMovie.getListMovie());
                bundle.putString(Constants.KEY_NAME_CATEGORY, categoryMovie.getNameCategory());
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listCategory.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCategory;
        private RecyclerView rcMovie;
        private TextView tvViewMore;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tv_category);
            rcMovie = itemView.findViewById(R.id.re_movie);
            tvViewMore = itemView.findViewById(R.id.tv_view_more);


        }
    }

}
