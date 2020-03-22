package com.example.myprojectapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myprojectapplication.imdbProp.MovieProperties;

import java.util.ArrayList;
import java.util.List;

public class MovieDatabase extends SQLiteOpenHelper {
    private static final String TAG = "MovieDatabase";
    String TableName="tblMovie";

    public MovieDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_Table_movie="create table "+TableName+"(id integer primary key autoincrement,title text,year text,poster text,director text,actors text,genre text,country text,language text)";
        db.execSQL(create_Table_movie);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String title, String year, String poster,String director, String actors, String genre, String country, String language){
        try {
            String insert_data="insert into "+TableName+"(title,year,poster,director,actors,genre,country,language) values('"+title+"','"+year+"','"+poster+"','"+director+"','"+actors+"','"+genre+"','"+country+"','"+language+"')";
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            sqLiteDatabase.execSQL(insert_data);
        }catch (SQLiteException e){
            Log.d(TAG, "insert : "+e.getMessage());
        }

    }
    public List<MovieProperties> getMovieProperties(){
        List<MovieProperties> moviePropertiesList=new ArrayList<>();
        String select_all="select title,year,poster,director,actors,genre,country,language from "+TableName;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(select_all, null);
        while (cursor.moveToNext()){
            MovieProperties movieProperties=new MovieProperties();
            movieProperties.setTitle(cursor.getString(0));
            movieProperties.setYear(cursor.getString(1));
            movieProperties.setPoster(cursor.getString(2));
            movieProperties.setDirector(cursor.getString(3));
            movieProperties.setActors(cursor.getString(4));
            movieProperties.setGenre(cursor.getString(5));
            movieProperties.setCountry(cursor.getString(6));
            movieProperties.setLanguage(cursor.getString(7));
            moviePropertiesList.add(movieProperties);
        }
        return moviePropertiesList;
    }
}
