package com.satyajit.movielist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Movie>movieList;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_View);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //to instantiate requestQueue we will make another class
        //we create singleton class to reduce the delay of loading data
        //only one instance will be created

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        movieList = new ArrayList<>();
        fetchMovie();

    }

    private void fetchMovie() {
        String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=1df75c6409532ac387b6a8e542dad9cc";
        JsonObjectRequest root = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray result = response.getJSONArray("results");
                    for(int i = 0;i<result.length();i++){
                        JSONObject jsonObject = result.getJSONObject(i);
                        String movieName = jsonObject.getString("title");
                        String overView = jsonObject.getString("overview");
                        String poster = jsonObject.getString("poster_path");
                        String ratings = jsonObject.getString("vote_average");

                        Movie movie = new Movie(overView,poster,movieName,ratings);
                        movieList.add(movie);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Adapter adapter = new Adapter(MainActivity.this,movieList);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(root);
    }
}