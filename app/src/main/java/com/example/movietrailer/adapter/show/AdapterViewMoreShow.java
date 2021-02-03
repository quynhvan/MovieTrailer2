package com.example.movietrailer.adapter.show;

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
import com.example.movietrailer.model.show.ApiResponseShow;

import java.util.ArrayList;

public class AdapterViewMoreShow extends RecyclerView.Adapter<AdapterViewMoreShow.MyViewHolder> implements Filterable {
    private ArrayList<ApiResponseShow.Show> listMovie = new ArrayList<>();
    private ArrayList<ApiResponseShow.Show> listMovieFull = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    private OnClickItemInViewMore onClickItemInViewMore;
    private boolean isFavorite = false;

    public AdapterViewMoreShow(Context context, ArrayList<ApiResponseShow.Show> listMovie, OnClickItemInViewMore onClickItemInViewMore) {
        this.context = context;
        this.listMovie = listMovie;
        this.onClickItemInViewMore = onClickItemInViewMore;
        this.listMovieFull = listMovie;
        inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public AdapterViewMoreShow.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view_more, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterViewMoreShow.MyViewHolder holder, int position) {
        ApiResponseShow.Show movie = listMovie.get(position);
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
                ArrayList<ApiResponseShow.Show> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(listMovieFull);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (ApiResponseShow.Show i : listMovieFull) {
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
                listMovie.add((ApiResponseShow.Show) results.values);
                notifyDataSetChanged();
            }
        };
        return filter;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvStar;
        ImageView ivMovie;
        ImageView ivFavorite;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStar = itemView.findViewById(R.id.tv_star_rate);
            tvTitle = itemView.findViewById(R.id.tv_title);
            ivMovie = itemView.findViewById(R.id.iv_anh);
//            ivFavorite.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(isFavorite){
//                        ivFavorite.setImageResource(R.drawable.ic_favorite_red);
//                        isFavorite = false;
//                    }
//                    else{
//                        ivFavorite.setImageResource(R.drawable.ic_love_white);
//                        isFavorite = true;
//                    }
//                }
//            });
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
