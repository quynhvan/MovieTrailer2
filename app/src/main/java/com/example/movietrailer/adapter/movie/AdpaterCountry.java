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

import java.util.ArrayList;

public class AdpaterCountry extends RecyclerView.Adapter<AdpaterCountry.MyViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<ApiResponseMovieDetail.ProductionCountry> listCountry = new ArrayList<>();

    public AdpaterCountry(Context context, ArrayList<ApiResponseMovieDetail.ProductionCountry> listCountry) {
        this.context = context;
        this.listCountry = listCountry;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AdpaterCountry.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_country,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdpaterCountry.MyViewHolder holder, int position) {
        ApiResponseMovieDetail.ProductionCountry itemCountry = listCountry.get(position);
        holder.tvCountry.setText(itemCountry.getName()+" ");
    }

    @Override
    public int getItemCount() {
        return listCountry.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvCountry;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCountry = itemView.findViewById(R.id.tv_country);
        }
    }
}
