package com.example.angithadas.moviedbapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.elmargomez.typer.Font;
import com.elmargomez.typer.Typer;
import com.example.angithadas.moviedbapps.R;
import com.example.angithadas.moviedbapps.activity.DetailActivity;
import com.example.angithadas.moviedbapps.model.MovieResults;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;




public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private final ArrayList<MovieResults> result;
    private final Context context;
    private static final String IMAGE_URL = "https://image.tmdb.org/t/p/w300_and_h450_bestv2";


    public MovieListAdapter(ArrayList<MovieResults> results, Context activity) {
        this.result = results;
        this.context= activity;

    }


    @Override
    public MovieListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieListAdapter.ViewHolder viewHolder, int i) {
        final MovieResults movie =  result.get(i);
        viewHolder.title.setText(movie.getTitle());

        if(movie.getVote_average() == null || movie.getVote_average().contentEquals("0")) {
            viewHolder.cardItem.setVisibility(View.GONE);
        }
        else{
            viewHolder.rating.setText(movie.getVote_average());
        }

        Picasso.with(context).load(IMAGE_URL+movie.getPoster_path()).into(viewHolder.image_View);

        viewHolder.image_View.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {


                //Log.d("click","clicked");

                Intent i;
                i = new Intent(v.getContext(),DetailActivity.class);
                i.putExtra("movie", movie);

                v.getContext().startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView title;
        private final ImageView image_View;
        private final TextView rating;
        private final LinearLayout cardItem;
        ViewHolder(View view) {
            super(view);

            title = (TextView)view.findViewById(R.id.movie_textview);
            title.setTypeface(Typer.set(context).getFont(Font.ROBOTO_MEDIUM_ITALIC));
            image_View=(ImageView)view.findViewById(R.id.movie_imageview);
            rating =(TextView) view.findViewById(R.id.movie_Rating);
            cardItem = (LinearLayout) view.findViewById(R.id.rating);
        }
    }
}