package com.example.movietrailer.adapter.movie;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movietrailer.Constants;
import com.example.movietrailer.R;
import com.example.movietrailer.activity.MovieDetail;
import com.example.movietrailer.model.movie.ApiResponseMovie;
import com.example.movietrailer.model.movie.ApiResponseMovieDetail;

import java.net.Inet4Address;
import java.util.ArrayList;

public class AdapterMovieItem extends RecyclerView.Adapter<AdapterMovieItem.MyViewHolder> {
    private Context context;
    private ArrayList<ApiResponseMovie.Movie> listMovies;
    private LayoutInflater inflater;
    private OnClickItemMovie onClickItemMovie;
    private ApiResponseMovieDetail apiResponseMovieDetail;
    private boolean favorite = false;


    public AdapterMovieItem(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<ApiResponseMovie.Movie> listMovies) {
        this.listMovies = listMovies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_movie, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ApiResponseMovie.Movie itemMovie = listMovies.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetail.class);
                intent.putExtra(Constants.KEY_ID_MOVIE, itemMovie.getId());
                v.getContext().startActivity(intent);
            }
        });

        holder.tvTitle.setText(itemMovie.getTitle());
        holder.tvStar.setText(Double.toString(Math.round(itemMovie.getPopularity()) / 10));
        holder.tvDate.setText(itemMovie.getReleaseDate());
        holder.progressBar.setProgress((int) (itemMovie.getPopularity() / 10));
        holder.progressBar.setMax((int) progressSetMax() / 10);
        Glide.with(context).load(Constants.KEY_BASE_IMAGE_URL + itemMovie.getBackdropPath()).into(holder.ivPoster);

    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvStar;
        private TextView tvDate;
        private ImageView ivPoster;
        private ImageView ivFavorite;
        private ProgressBar progressBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvTitle = itemView.findViewById(R.id.tv_title_movie);
            tvStar = itemView.findViewById(R.id.tv_progress_bar);
            ivPoster = itemView.findViewById(R.id.iv_poster_movie);
            progressBar = itemView.findViewById(R.id.progress_bar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItemMovie.onClickItemMovie(getAdapterPosition());

                }
            });

        }
    }

    public interface OnClickItemMovie {
        void onClickItemMovie(int position);
    }

    private double progressSetMax() {
        double max = 0;
        for (ApiResponseMovie.Movie i : listMovies) {
            if (i.getPopularity() >= max) {
                max = i.getPopularity();
            }
        }
        return max;
    }
}
