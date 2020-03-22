package com.example.myprojectapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cz.msebera.android.httpclient.Header;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myprojectapplication.imdb.MovieList;
import com.example.myprojectapplication.imdb.Search;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;

public class JsonToRecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_to_recycler);
        Intent intent=getIntent();
        String title = intent.getStringExtra("Title");
        String address="http://www.omdbapi.com/?s="+title+"&apikey=70ad462a";
        AsyncHttpClient client=new AsyncHttpClient();
        client.get(address,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Gson gson=new Gson();
                MovieList movieList = gson.fromJson(response.toString(), MovieList.class);
                if (movieList.getSearch()!=null){
                    RecyclerView recyclerView=findViewById(R.id.recyclerView);
                    MovieAdopter  movieAdopter=new MovieAdopter(movieList.getSearch());
                    recyclerView.setAdapter(movieAdopter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(JsonToRecyclerActivity.this,RecyclerView.VERTICAL,false));

                }else {
                    TextView textView=findViewById(R.id.txtTitle);
                    textView.setText("Not Found Movie");
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
