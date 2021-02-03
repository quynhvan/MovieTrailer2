package com.example.movietrailer.adapter.show;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movietrailer.R;
import com.example.movietrailer.model.movie.ApiResponseMovieReview;
import com.example.movietrailer.model.movie.ImageAccount;
import com.example.movietrailer.model.show.ApiResponseShowReview;

import java.util.ArrayList;

public class AdapterShowReview extends RecyclerView.Adapter<AdapterShowReview.MyViewHolder> {
    private Context context;
    private ArrayList<ApiResponseShowReview.Results> listReview = new ArrayList<>();
    private ArrayList<ImageAccount> listAccount = new ArrayList<>();
    private LayoutInflater inflater;

    public AdapterShowReview(Context context, ArrayList<ApiResponseShowReview.Results> listReview) {
        this.context = context;
        this.listReview = listReview;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AdapterShowReview.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_review,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterShowReview.MyViewHolder holder, int position) {
        ApiResponseShowReview.Results itemReview = listReview.get(position);
        holder.tvName.setText(itemReview.getAuthor());
        holder.tvContent.setText(itemReview.getContent());
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
            tvContent = itemView.findViewById(R.id.tv_content);
            tvName= itemView.findViewById(R.id.tv_name);
        }
    }
}
