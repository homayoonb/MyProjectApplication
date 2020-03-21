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
                if (editText.getText().equals(' '))
                {
                    Toast.makeText(getApplicationContext(),"please Enter title movie",Toast.LENGTH_LONG).show();
                    editText.setFocusable(true);
                }else {
                    Intent intent = new Intent(MainActivity.this, JsonToRecyclerActivity.class);
                    intent.putExtra("Title", editText.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}
