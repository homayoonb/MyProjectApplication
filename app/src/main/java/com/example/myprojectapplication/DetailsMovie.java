package com.example.myprojectapplication;

import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myprojectapplication.imdbProp.MovieProperties;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class DetailsMovie extends AppCompatActivity {
    MovieDatabase movieDatabase=new MovieDatabase(DetailsMovie.this,"imdb",null,1);
    private static final String TAG = "DetailsMovie";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);
        Intent intent=getIntent();
        String id = intent.getStringExtra("id");
        String address="http://www.omdbapi.com/?i="+id+"&apikey=70ad462a";
        AsyncHttpClient client=new AsyncHttpClient();
        client.get(address,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Gson gson=new Gson();
                MovieProperties movieProperties = gson.fromJson(response.toString(), MovieProperties.class);
                final String title = movieProperties.getTitle();
                final String year = movieProperties.getYear();
                final String director = movieProperties.getDirector();
                final String genre = movieProperties.getGenre();
                final String country = movieProperties.getCountry();
                final String language = movieProperties.getLanguage();
                final String actors = movieProperties.getActors();
                final TextView txtDetailsTitle = findViewById(R.id.txtDetailsTitle);
                final TextView txtYear = findViewById(R.id.txtYear);
                final TextView txtDirector=findViewById(R.id.txtDirector);
                final TextView txtGenre=findViewById(R.id.txtGenre);
                final TextView txtCountry=findViewById(R.id.txtCountry);
                TextView txtLanguage=findViewById(R.id.txtLanguage);
                final TextView txtActors = findViewById(R.id.txtActors);
                txtDetailsTitle.setText("Title :"+title);
                txtYear.setText("Year :"+year);
                txtDirector.setText("Director :"+director);
                txtGenre.setText("Genre :"+genre);
                txtCountry.setText("Country :"+country);
                txtLanguage.setText("Language :"+language);
                txtActors.setText("Actors :"+actors);
                ImageView imageView=findViewById(R.id.imgPoster);
                final String poster = movieProperties.getPoster();
                Picasso.get().load(poster).into(imageView);
                Button btnsavetodatabase=findViewById(R.id.btnsavetodatabase);
                btnsavetodatabase.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        movieDatabase.insert(title,year,poster,director,actors,genre,country,language);
                        Toast.makeText(DetailsMovie.this, "Save Is Success", Toast.LENGTH_SHORT).show();
                        Intent intent1=new Intent(DetailsMovie.this,ShowMovieLocal.class);
                        startActivity(intent1);
                    }
                });
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
