package com.example.movietrailer.adapter.show;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movietrailer.Constants;
import com.example.movietrailer.R;
import com.example.movietrailer.activity.ShowViewMore;
import com.example.movietrailer.model.show.CategoryShow;

import java.util.ArrayList;

public class AdapterShowCategory extends RecyclerView.Adapter<AdapterShowCategory.MyViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<CategoryShow> listCategory = new ArrayList<>();

    public AdapterShowCategory(Context context, ArrayList<CategoryShow> listCategory) {
        this.context = context;
        this.listCategory = listCategory;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AdapterShowCategory.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_category, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterShowCategory.MyViewHolder holder, int position) {
        CategoryShow categoryShow = listCategory.get(position);
        holder.tvCategory.setText(categoryShow.getNameCategory());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        holder.rcMovie.setLayoutManager(linearLayoutManager);

        AdapterShowItem adapterShowItem = new AdapterShowItem(context);
        adapterShowItem.setData(categoryShow.getListShow());
        holder.rcMovie.setAdapter(adapterShowItem);

        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tvViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowViewMore.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(Constants.KEY_VIEW_MORE_MOVIE, categoryShow.getListShow());
                bundle.putString(Constants.KEY_NAME_CATEGORY, categoryShow.getNameCategory());
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCategory.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCategory;
        private RecyclerView rcMovie;
        private TextView tvViewMore;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tv_category);
            rcMovie = itemView.findViewById(R.id.re_movie);
            tvViewMore = itemView.findViewById(R.id.tv_view_more);


        }
    }

}
