package com.example.myprojectapplication;

import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myprojectapplication.imdbProp.MovieProperties;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class DetailsMovie extends AppCompatActivity {

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
                TextView txtDetailsTitle = findViewById(R.id.txtDetailsTitle);
                TextView txtYear = findViewById(R.id.txtYear);
                TextView txtDirector=findViewById(R.id.txtDirector);
                TextView txtGenre=findViewById(R.id.txtGenre);
                TextView txtCountry=findViewById(R.id.txtCountry);
                TextView txtLanguage=findViewById(R.id.txtLanguage);
                TextView txtActors = findViewById(R.id.txtActors);
                txtDetailsTitle.setText(movieProperties.getTitle());
                txtYear.setText(movieProperties.getYear());
                txtDirector.setText(movieProperties.getDirector());
                txtGenre.setText(movieProperties.getGenre());
                txtCountry.setText(movieProperties.getCountry());
                txtLanguage.setText(movieProperties.getLanguage());
                txtActors.setText(movieProperties.getActors());
                ImageView imageView=findViewById(R.id.imgPoster);
                String poster = movieProperties.getPoster();
                Picasso.get().load(poster).into(imageView);
                Button btnsavetodatabase=findViewById(R.id.btnsavetodatabase);
                btnsavetodatabase.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

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
