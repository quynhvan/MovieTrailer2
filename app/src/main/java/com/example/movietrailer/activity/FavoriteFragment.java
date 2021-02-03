package com.example.movietrailer.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database.Detail;
import com.example.database.MovieViewModel;
import com.example.movietrailer.R;
import com.example.movietrailer.adapter.AdapterFavorite;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;
import java.util.List;

public class FavoriteFragment extends Fragment {
    private RecyclerView rcv_fav;
    private AdapterFavorite adapterFavorite;
    private MovieViewModel movieViewModel;
    private List<Detail> listItem;
    private Toolbar toolbar;
    private ConstraintLayout layout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        toolbar = view.findViewById(R.id.toolbar_fav);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        rcv_fav = view.findViewById(R.id.rcv_favorite);
        layout = view.findViewById(R.id.layout_main_activity);
        movieViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(MovieViewModel.class);
        adapterFavorite = new AdapterFavorite(getContext());
        movieViewModel.getAllMovies().observe(this, new Observer<List<Detail>>() {
            @Override
            public void onChanged(List<Detail> details) {
                adapterFavorite.setFavMovie(details);
                listItem = details;
                rcv_fav.setAdapter(adapterFavorite);
                adapterFavorite.notifyDataSetChanged();
            }
        });
        initView();
        swipe();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_fav.setLayoutManager(linearLayoutManager);
        rcv_fav.setHasFixedSize(true);
        toolbar.setTitle("Favorite List");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    private void swipe() {

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            //ham onMove xử lý code drag and drop
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder dragged, @NonNull RecyclerView.ViewHolder target) {
                int positionDragged = dragged.getAdapterPosition();
                int positioTarget = target.getAdapterPosition();
                Collections.swap(listItem,positionDragged,positioTarget);
                adapterFavorite.notifyItemMoved(positionDragged,positioTarget);
                return false;
            }

            @Override
            // hàm onSwipe xử  lý code trượt xóa
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final Detail detail = listItem.get(viewHolder.getAdapterPosition());
                final int deletedIndex = viewHolder.getAdapterPosition();
                movieViewModel.RemoveFavList(adapterFavorite.getMovieAt(viewHolder.getAdapterPosition()));
                adapterFavorite.notifyDataSetChanged();
                Snackbar snackbar = Snackbar.make(layout, " Deleted from Favorite list", Snackbar.LENGTH_SHORT);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        movieViewModel.addFavList(detail);
                        adapterFavorite.notifyDataSetChanged();
                    }
                });
                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
            }

        });
        itemTouchHelper.attachToRecyclerView(rcv_fav);

    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_favorite, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                allNotesDelete();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void allNotesDelete() {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.warning)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        movieViewModel.deleteAllMovies();
                        adapterFavorite.notifyDataSetChanged();
                        Toast.makeText(getContext(), "All favorite movies deleted!", Toast.LENGTH_SHORT).show();                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        AlertDialog dialog = builder.create();
        builder.show();
    }
}
