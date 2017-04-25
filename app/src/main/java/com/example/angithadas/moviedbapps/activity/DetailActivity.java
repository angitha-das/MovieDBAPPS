package com.example.angithadas.moviedbapps.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.elmargomez.typer.Font;
import com.elmargomez.typer.Typer;
import com.example.angithadas.moviedbapps.R;
import com.example.angithadas.moviedbapps.model.MovieResults;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private ImageView imageView1;
    private ImageView imageView2;
    private TextView overview_movie;
    private TextView releaseDate;
    private TextView rating;
    private RelativeLayout RLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private static final String IMAGE_URL = "https://image.tmdb.org/t/p/w300_and_h450_bestv2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        initialize();

        toolbar.setTitle("");

        appBarLayout.addOnOffsetChangedListener(this);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        MovieResults movie = (MovieResults) getIntent().getSerializableExtra("movie");

        toolbar.setTitle(movie.getTitle());

        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white));

        //Lazy Loading for loading backdrop and poster images
        Picasso.with(this).load(IMAGE_URL + movie.getPoster_path()).into(imageView1);
        Picasso.with(this).load(IMAGE_URL + movie.getBackdrop_path()).into(imageView2);

        overview_movie.setText(movie.getOverview());
        releaseDate.setText(movie.getRelease_date());

        //Check for vote_average of movies
        if (movie.getVote_average() == null || movie.getVote_average().contentEquals("0")) {
            RLayout.setVisibility(View.GONE);
        } else {
            String ratingOnTen = movie.getVote_average()+"/10";
            rating.setText(ratingOnTen);
        }
    }


   private void initialize() {

        imageView1 = (ImageView) findViewById(R.id.imageView_Backdrop);
        imageView2 = (ImageView) findViewById(R.id.imageView_Poster);
        overview_movie = (TextView) findViewById(R.id.movie_Overview);
        overview_movie.setTypeface(Typer.set(this).getFont(Font.ROBOTO_REGULAR));
        releaseDate = (TextView) findViewById(R.id.movie_ReleaseDate);
        releaseDate.setTypeface(Typer.set(this).getFont(Font.ROBOTO_REGULAR));
        rating = (TextView) findViewById(R.id.movie_Rating);
        rating.setTypeface(Typer.set(this).getFont(Font.ROBOTO_REGULAR));
        RLayout = (RelativeLayout) findViewById(R.id.rating_parent);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);


    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset != 0) {
            // Collapsed
            imageView1.setVisibility(View.GONE);
        } else {
            // Not collapsed
            imageView1.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);

    }
}
