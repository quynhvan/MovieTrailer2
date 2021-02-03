package com.example.movietrailer.adapter.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movietrailer.Constants;
import com.example.movietrailer.model.movie.ApiResponseMovie;
import com.example.movietrailer.R;

import java.util.ArrayList;

public class AdapterViewMore extends RecyclerView.Adapter<AdapterViewMore.MyViewHolder> implements Filterable {
    private ArrayList<ApiResponseMovie.Movie> listMovie = new ArrayList<>();
    private ArrayList<ApiResponseMovie.Movie> listMovieFull = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    private OnClickItemInViewMore onClickItemInViewMore;
    private boolean isFavorite = false;

    public AdapterViewMore(Context context, ArrayList<ApiResponseMovie.Movie> listMovie, OnClickItemInViewMore onClickItemInViewMore) {
        this.context = context;
        this.listMovie = listMovie;
        this.onClickItemInViewMore = onClickItemInViewMore;
        this.listMovieFull = listMovie;
        inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public AdapterViewMore.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view_more, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterViewMore.MyViewHolder holder, int position) {
        ApiResponseMovie.Movie movie = listMovie.get(position);
        holder.tvStar.setText(Double.toString(movie.getVoteAverage()));
        holder.tvTitle.setText(movie.getTitle());
        Glide.with(context).load(Constants.KEY_BASE_IMAGE_URL + movie.getPosterPath()).into(holder.ivMovie);

    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                String searchChr = constraint.toString().toLowerCase();
                if(searchChr == null || searchChr.length() > 0){
                    filterResults.count = listMovieFull.size();
                    filterResults.values = listMovieFull;
                }
                else{
                    
                }
                ArrayList<ApiResponseMovie.Movie> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(listMovieFull);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (ApiResponseMovie.Movie i : listMovieFull) {
                        if (i.getTitle().toLowerCase().contains(filterPattern)) {
                            filteredList.add(i);
                        }
                    }
                }

                filterResults.count = filteredList.size();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listMovie.clear();
                listMovie.add((ApiResponseMovie.Movie) results.values);
                notifyDataSetChanged();
            }
        };
        return filter;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvStar;
        ImageView ivMovie;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStar = itemView.findViewById(R.id.tv_star_rate);
            tvTitle = itemView.findViewById(R.id.tv_title);
            ivMovie = itemView.findViewById(R.id.iv_anh);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItemInViewMore.onClick(getAdapterPosition());
                }
            });
        }
    }
    public interface OnClickItemInViewMore{
        void onClick(int position);
    }

}
