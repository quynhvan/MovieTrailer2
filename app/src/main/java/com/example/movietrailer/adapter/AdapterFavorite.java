package com.example.movietrailer.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.database.Detail;
import com.example.database.MovieRepository;
import com.example.database.MovieViewModel;
import com.example.movietrailer.Constants;
import com.example.movietrailer.R;
import com.example.movietrailer.activity.MovieDetail;
import com.example.movietrailer.activity.ShowDetail;
import com.example.movietrailer.model.ConnectServer;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterFavorite extends RecyclerView.Adapter<AdapterFavorite.MyviewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<Detail> listFav;
    private MovieViewModel movieViewModel;

    public AdapterFavorite(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setFavMovie(List<Detail> listFav) {
        this.listFav = listFav;
        notifyDataSetChanged();
    }
    public Detail getMovieAt(int position){
        return listFav.get(position);
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_favorite, parent, false);
        return new MyviewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        Detail movie = listFav.get(position);
        if("Movie".equals(movie.getCategory())) {
            ConnectServer.getApiService().getDetail(movie.getId()).enqueue(new Callback<Detail>() {
                @Override
                public void onResponse(Call<Detail> call, Response<Detail> response) {
                    if (response.isSuccessful()) {
                        Detail detail = response.body();
                        holder.tvTitle.setText(detail.getTitle());
                        holder.tvRelease.setText(detail.getReleaseDate());
                        holder.tvVote.setText(Double.toString(detail.getVoteAverage()));
                        holder.tvCategory.setText("Movie");
                        Glide.with(context).load(Constants.KEY_BASE_IMAGE_URL + detail.getPosterPath()).into(holder.ivAnh);
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context, MovieDetail.class);
                                intent.putExtra(Constants.KEY_ID_MOVIE, movie.getId());
                                v.getContext().startActivity(intent);
                            }
                        });

                    }
                }

                @Override
                public void onFailure(Call<Detail> call, Throwable t) {
                }
            });
        }
        if("TV".equals(movie.getCategory())){



       ConnectServer.getApiService().DetailShow(movie.getId()).enqueue(new Callback<Detail>() {
           @Override
           public void onResponse(Call<Detail> call, Response<Detail> response) {
               if(response.isSuccessful()){
                   Detail detail = response.body();
                   holder.tvTitle.setText(detail.getName());
                   holder.tvRelease.setText(detail.getFirstAirDate());
                   holder.tvVote.setText(Double.toString(detail.getVoteAverage()));
                   holder.tvCategory.setText("TV");
                   Glide.with(context).load(Constants.KEY_BASE_IMAGE_URL + detail.getPosterPath()).into(holder.ivAnh);
                   holder.itemView.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Intent intent = new Intent(context, ShowDetail.class);
                           intent.putExtra(Constants.KEY_ID_SHOW, movie.getId());
                           v.getContext().startActivity(intent);
                       }
                   });
               }
           }

           @Override
           public void onFailure(Call<Detail> call, Throwable t) {

           }
       });
        }
    }
    @Override
    public int getItemCount() {
        return listFav.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvRelease, tvVote, tvCategory;
        ImageView ivAnh;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            tvRelease = itemView.findViewById(R.id.tv_release);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvVote = itemView.findViewById(R.id.tv_vote);
            ivAnh = itemView.findViewById(R.id.iv_poster);
            tvCategory = itemView.findViewById(R.id.tv_category);

        }
    }

    public interface OnClickFav {
        void onClick(Detail detail);
    }

}
