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
import com.example.movietrailer.Constants;
import com.example.movietrailer.model.movie.ApiResponseMovieCast;
import com.example.movietrailer.R;

import java.util.ArrayList;

public class AdapterCast extends RecyclerView.Adapter<AdapterCast.MyViewHolder> {
    private Context context;
    private ArrayList<ApiResponseMovieCast.Cast> listCast = new ArrayList<>();
    private LayoutInflater inflater;

    public AdapterCast(Context context, ArrayList<ApiResponseMovieCast.Cast> listCast) {
        this.context = context;
        this.listCast = listCast;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_cast, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ApiResponseMovieCast.Cast itemCast = listCast.get(position);
        holder.tvName.setText(itemCast.getName());
        Glide.with(context).load(Constants.KEY_BASE_IMAGE_URL + itemCast.getProfilePath()).into(holder.ivCast);
    }

    @Override
    public int getItemCount() {
        return listCast.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCast;
        TextView tvName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCast = itemView.findViewById(R.id.iv_anh);
            tvName = itemView.findViewById(R.id.tv_name_cast);
        }
    }
}
