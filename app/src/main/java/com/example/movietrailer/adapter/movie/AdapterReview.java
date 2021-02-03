package com.example.movietrailer.adapter.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.database.Detail;
import com.example.movietrailer.Constants;
import com.example.movietrailer.R;
import com.example.movietrailer.model.ConnectServer;
import com.example.movietrailer.model.movie.ApiResponseMovieReview;
import com.example.movietrailer.model.movie.ImageAccount;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterReview extends RecyclerView.Adapter<AdapterReview.MyViewHolder> {
    private Context context;
    private ArrayList<ApiResponseMovieReview.ReviewMovie> listReview = new ArrayList<>();
    private int idMovieReview, idTvReview;
    private LayoutInflater inflater;

    public AdapterReview(Context context, ArrayList<ApiResponseMovieReview.ReviewMovie> listReview) {
        this.context = context;
        this.listReview = listReview;
        inflater = LayoutInflater.from(context);
    }
    public void getIdReviewMovie(int idMovieReview){
        this.idMovieReview = idMovieReview;
    }
    public void getIdTvReview(int idTvReview){
        this.idTvReview = idTvReview;
    }

    @NonNull
    @Override
    public AdapterReview.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_review, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterReview.MyViewHolder holder, int position) {
        ApiResponseMovieReview.ReviewMovie itemReview = listReview.get(position);
        holder.tvName.setText(itemReview.getAuthor());
        holder.tvContent.setText(itemReview.getContent());
        ConnectServer.getApiService().getDetail(idMovieReview).enqueue(new Callback<Detail>() {
            @Override
            public void onResponse(Call<Detail> call, Response<Detail> response) {
                if (response.isSuccessful()) {
                    Detail detail = response.body();
                    Glide.with(context).load(Constants.KEY_BASE_IMAGE_URL + detail.getPosterPath()).into(holder.ivReviewer);
                }
            }
            @Override
            public void onFailure(Call<Detail> call, Throwable t) {

            }
        });
        ConnectServer.getApiService().DetailShow(idTvReview).enqueue(new Callback<Detail>() {
            @Override
            public void onResponse(Call<Detail> call, Response<Detail> response) {
                if(response.isSuccessful()){
                    Detail detail = response.body();
                    Glide.with(context).load(Constants.KEY_BASE_IMAGE_URL + detail.getPosterPath()).into(holder.ivReviewer);
                }
            }

            @Override
            public void onFailure(Call<Detail> call, Throwable t) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return listReview.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivReviewer;
        TextView tvContent;
        TextView tvName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivReviewer = itemView.findViewById(R.id.iv_reviewer);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

}
