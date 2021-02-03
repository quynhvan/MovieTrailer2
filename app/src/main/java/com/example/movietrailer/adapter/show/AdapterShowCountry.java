package com.example.movietrailer.adapter.show;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movietrailer.R;
import com.example.movietrailer.model.show.ApiResponseShowDetail;

import java.util.ArrayList;

public class AdapterShowCountry extends RecyclerView.Adapter<AdapterShowCountry.MyViewHolder> {
    private Context context;
    private ArrayList<ApiResponseShowDetail.Networks> listCountry = new ArrayList<>();
    private LayoutInflater inflater;

    public AdapterShowCountry(Context context, ArrayList<ApiResponseShowDetail.Networks> listCountry) {
        this.context = context;
        this.listCountry = listCountry;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AdapterShowCountry.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_country,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterShowCountry.MyViewHolder holder, int position) {
        ApiResponseShowDetail.Networks country = listCountry.get(position);
        holder.tvCountry.setText(country.getOriginCountry()+" ");
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCountry;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCountry = itemView.findViewById(R.id.tv_country);
        }
    }
}
