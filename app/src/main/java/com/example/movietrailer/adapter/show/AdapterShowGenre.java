package com.example.movietrailer.adapter.show;

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

public class AdapterShowGenre extends RecyclerView.Adapter<AdapterShowGenre.MyViewHolder> {
    private Context context;
    private ArrayList<ApiResponseShowDetail.Genres> listShowGenre = new ArrayList<>();
    private List<ApiResponseMovieDetail.Genre> genreList;
    private LayoutInflater inflater;

    public AdapterShowGenre(Context context, ArrayList<ApiResponseShowDetail.Genres> listShowGenre) {
        this.context = context;
        this.listShowGenre = listShowGenre;
        inflater = LayoutInflater.from(context);
    }
    public void setListGenre(List<ApiResponseMovieDetail.Genre> listGenres){
        this.genreList = listGenres;
    }
    @NonNull
    @Override
    public AdapterShowGenre.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_genres,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterShowGenre.MyViewHolder holder, int position) {
        ApiResponseShowDetail.Genres itemgenre = listShowGenre.get(position);
        holder.tvGenre.setText(itemgenre.getName());
    }

    @Override
    public int getItemCount() {
        return listShowGenre.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvGenre;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGenre = itemView.findViewById(R.id.genre);
        }
    }
}
