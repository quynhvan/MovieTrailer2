
package com.example.movietrailer.adapter.show;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movietrailer.Constants;
import com.example.movietrailer.activity.ShowActivity;
import com.example.movietrailer.activity.ShowDetail;
import com.example.movietrailer.model.show.ApiResponseShow;
import com.example.movietrailer.R;

import java.util.ArrayList;

public class AdapterShowItem extends RecyclerView.Adapter<AdapterShowItem.MyViewHolder> {
    private Context context;
    private ArrayList<ApiResponseShow.Show> listItemShow = new ArrayList<>();
    private LayoutInflater inflater;
    public OnclickItemShow onClickItemShow;
    private int idMovie;

    public AdapterShowItem(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);

    }
    public void setData(ArrayList<ApiResponseShow.Show> listItemShow){
        this.listItemShow = listItemShow;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public AdapterShowItem.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_movie, parent, false);
        return new AdapterShowItem.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterShowItem.MyViewHolder holder, int position) {
        ApiResponseShow.Show itemMovie = listItemShow.get(position);
        MyViewHolder myViewHolder = (MyViewHolder)holder;
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowDetail.class);
                intent.putExtra(Constants.KEY_ID_SHOW, itemMovie.getId());
                v.getContext().startActivity(intent);
            }
        });

        holder.tvTitle.setText(itemMovie.getTitle());
        holder.tvStar.setText(Double.toString(Math.round(itemMovie.getPopularity())/10));
        holder.tvDate.setText(itemMovie.getFirstAirDate());
        Glide.with(context).load(Constants.KEY_BASE_IMAGE_URL + itemMovie.getBackdropPath()).into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return listItemShow.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvStar;
        private TextView tvDate;
        private ImageView ivPoster;
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
                    onClickItemShow.onClickItemShow(getAdapterPosition());
                }
            });

        }
    }

    public interface OnclickItemShow {
        void onClickItemShow(int position);
    }
}


