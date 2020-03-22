package com.example.myprojectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText=findViewById(R.id.EditTextTitle);
        Button button=findViewById(R.id.btnSearch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(editText.getText().equals(null)))
                {
                    Intent intent = new Intent(MainActivity.this, JsonToRecyclerActivity.class);
                    intent.putExtra("Title", editText.getText().toString());
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "Please Enter title", Toast.LENGTH_SHORT).show();
                    editText.setFocusable(true);
                }
            }
        });
        Button btnShowMovie=findViewById(R.id.btnShowMovie);
        btnShowMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ShowMovieLocal.class);
                startActivity(intent);
            }
        });
    }
}
