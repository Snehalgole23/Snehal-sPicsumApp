package com.example.myapplicationtestmm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ItemDataActivity extends AppCompatActivity {

    TextView authorname;
    ImageView authorImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_data);


        //findview
        authorImage = findViewById(R.id.image);
        authorname = findViewById(R.id.name);


        //get intent
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String image = intent.getStringExtra("image");

        //set data

        authorname.setText(name);

        Glide.with(this)
                .load(image)
                .into(authorImage);
    }
}