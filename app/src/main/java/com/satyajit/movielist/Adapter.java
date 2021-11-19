package com.satyajit.movielist;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MovieHolder> {

    private Context context;
    private List<Movie> movieList;
    public Adapter(Context context,List<Movie> movie){
        this.context = context;
        this.movieList = movie;
    }
    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        //create an instance of movie class
        Movie movie = movieList.get(position);
        holder.ratings.setText(movie.getmRating());
        holder.movieName.setText(movie.getmMovieName());
        holder.overView.setText(movie.getmOverView());
        Glide.with(context).load(movie.getmPoster()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Toast.makeText(context.getApplicationContext(), "Image Load failed!!", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        }).into(holder.posterImage);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{

        private ImageView posterImage;
        private TextView movieName;
        private TextView overView;
        private TextView ratings;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            posterImage = itemView.findViewById(R.id.image_View);
            movieName= itemView.findViewById(R.id.movie_name_view);
            overView = itemView.findViewById(R.id.overview_view);
            ratings = itemView.findViewById(R.id.rating_view);
        }
    }
}
