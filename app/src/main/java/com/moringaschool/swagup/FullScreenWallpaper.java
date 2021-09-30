package com.moringaschool.swagup;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class FullScreenWallpaper extends AppCompatActivity {
 String originalUrl="originalUrl";
 ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_wallpaper);

//        getSupportActionBar().hide();

        Intent intent = getIntent();
        originalUrl = intent.getStringExtra("originalUrl");

        imageView = findViewById(R.id.image_view);

//        Glide.with(this).load(originalUrl).into(imageView);
        Picasso.get().load(originalUrl).into(imageView);
    }
}