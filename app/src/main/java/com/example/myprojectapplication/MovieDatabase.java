package com.example.myprojectapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class MovieDatabase extends SQLiteOpenHelper {
    String TableName="movieProperties";

    public MovieDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_Table_movie="create table "+TableName+"(id integer primary key autoincrement,title text,year text,rated text,released text,runtime text,genre text,director text,writer text,actors text,plot text,language text,country text,awards text,metascore text,imdbRating text,imdbVotes text,imdbID text,type text,dVD text,boxOffice text,production text,website text,response text)";
        db.execSQL(create_Table_movie);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insert(String title,String year,String rated,String released,String runtime,String genre,String director,String writer,String actors,String plot,String language,String country,String awards,String poster,String metascore,String imdbRating,String imdbVotes,String imdbID,String type,String dVD,String boxOffice,String production,String website){
        String insert_data="insert into "+TableName+"(title,year,rated,released,runtime,genre,director,writer,actors,plot,language,country,awards,poster,metascore,imdbRating,imdbVotes,imdbID,type,dVD,boxOffice,production,website) values(' "+title+" ' , ' "+year+" ',' "+rated+" ',' "+released+" ' , ' "+runtime+" ' , ' "+genre+" ' , ' "+director+" ' , ' "+writer+" ' , ' "+actors+" ' , ' "+plot+" ' , ' "+language+" ' , ' "+country+" ' , ' "+awards+" ' , ' "+poster+" ' , ' "+metascore+" ' , ' "+imdbRating+" ' , ' "+imdbVotes+" ' , ' "+imdbID+" ' , ' "+type+" ' , ' "+dVD+" ' , ' "+boxOffice+" ' , ' "+production+" ' , ' "+website+" ' )";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL(insert_data);
    }
}
