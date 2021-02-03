package com.example.movietrailer.adapter.show;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movietrailer.Constants;
import com.example.movietrailer.R;
import com.example.movietrailer.model.movie.ApiResponseMovieVideo;
import com.example.movietrailer.model.show.ApiResponseShowVideo;

import java.util.ArrayList;

public class AdapterShowVideo extends RecyclerView.Adapter<AdapterShowVideo.MyViewHolder> {
    private Context context;
    private ArrayList<ApiResponseShowVideo.Results> listVideo = new ArrayList<>();
    private LayoutInflater inflater;
    private OnClickItemVideo onClickItemVideo;

    public AdapterShowVideo(Context context, ArrayList<ApiResponseShowVideo.Results> listVideo, OnClickItemVideo onClickItemVideo) {
        this.context = context;
        this.listVideo = listVideo;
        inflater = LayoutInflater.from(context);
        this.onClickItemVideo = onClickItemVideo;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_video, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ApiResponseShowVideo.Results video = listVideo.get(position);
        Glide.with(context).load(Constants.KEY_BASE_THUMBNAILS + video.getKey()+Constants.KEY_BASE_THUMBNAILS_DEFAULTS).into(holder.ivPreview);
    }


    @Override
    public int getItemCount() {
        return listVideo.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPreview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPreview = itemView.findViewById(R.id.iv_preview_video);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItemVideo.onClick(getAdapterPosition());
                }
            });
        }
    }

    public interface OnClickItemVideo {
        void onClick(int position);
    }
}
