package com.example.myprojectapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myprojectapplication.imdb.MovieList;
import com.example.myprojectapplication.imdb.Search;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdopter extends RecyclerView.Adapter<MovieAdopter.MovieViewHolder> {
    List<Search> arrayList;

    public MovieAdopter(List<Search> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_items, parent, false);
        MovieViewHolder movieViewHolder=new MovieViewHolder(view);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        String title = arrayList.get(position).getTitle();
        holder.textView.setText("Title : "+title);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MovieViewHolder(@NonNull final View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.txtTitle);
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Search search=arrayList.get(getAdapterPosition());
                     Intent intent=new Intent(itemView.getContext(),DetailsMovie.class);
                     intent.putExtra("id",search.getImdbID());
                     itemView.getContext().startActivity(intent);
                 }
             });
        }
    }
}
