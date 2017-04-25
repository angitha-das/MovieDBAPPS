package com.example.angithadas.moviedbapps.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.angithadas.moviedbapps.R;
import com.example.angithadas.moviedbapps.adapter.MovieListAdapter;
import com.example.angithadas.moviedbapps.model.ServerResponse;
import com.example.angithadas.moviedbapps.network.Communicator;
import com.example.angithadas.moviedbapps.network.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieListFragment extends Fragment {
    private String category = "popular";

    private RecyclerView recyclerView;
    private ProgressDialog progress;


    @Override
    public void onResume() {
        super.onResume();

        if (isNetworkAvailable()) {
            if (category != null) {
                doApiCall();
            }
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "No Network Connection", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void doApiCall() {

        progress = new ProgressDialog(getActivity());
        progress.setMessage("Loading");
        progress.show();


        final RetrofitInterface apiService = Communicator.getClient().create(RetrofitInterface.class);

        String API_KEY = "d8aacde290a07f6d0b66f11c566932ff";
        final Call<ServerResponse> serverResponseCall = apiService.getMovieList(category, API_KEY);


        serverResponseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                RecyclerView.Adapter adapter = new MovieListAdapter(response.body().getResults(), getActivity());
                recyclerView.setAdapter(adapter);
                progress.dismiss();

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e("Failure", t.toString());
            }
        });
    }

    // newInstance constructor for creating fragment with arguments
    public static MovieListFragment newInstance(int page, String title) {
        MovieListFragment fragmentFirst = new MovieListFragment();

        Bundle args = new Bundle();

        args.putInt("someInt", page);
        args.putString("someTitle", title);


        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        category = getArguments().getString("someTitle");


    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            final GestureDetector gestureDetector = new GestureDetector(getActivity().getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    rv.getChildAdapterPosition(child);

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        return view;
    }

}
