package com.example.movietrailer.adapter.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movietrailer.model.movie.ApiResponseMovieDetail;
import com.example.movietrailer.R;
import com.example.movietrailer.model.show.ApiResponseShowDetail;

import java.util.ArrayList;
import java.util.List;

public class AdapterGenres extends RecyclerView.Adapter<AdapterGenres.MyViewHolder> {
    private Context context;
    private ArrayList<ApiResponseMovieDetail.Genre> listGenre = new ArrayList<>();
    private ArrayList<ApiResponseShowDetail.Genres> listShowGenre = new ArrayList<>();
    private List<ApiResponseMovieDetail.Genre> genreList;
    private LayoutInflater inflater;

    public AdapterGenres(Context context, ArrayList<ApiResponseMovieDetail.Genre> listGenre) {
        this.context = context;
        this.listGenre = listGenre;
        inflater = LayoutInflater.from(context);
    }
    public void setListGenre(List<ApiResponseMovieDetail.Genre> listGenres){
        this.genreList = listGenres;
    }
    @NonNull
    @Override
    public AdapterGenres.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_genres,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGenres.MyViewHolder holder, int position) {
        ApiResponseMovieDetail.Genre itemgenre = listGenre.get(position);
        holder.tvGenre.setText(itemgenre.getName());
    }

    @Override
    public int getItemCount() {
        return listGenre.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvGenre;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGenre = itemView.findViewById(R.id.genre);
        }
    }
}
