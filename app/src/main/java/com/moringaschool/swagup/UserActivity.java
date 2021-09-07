package com.moringaschool.swagup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.moringaschool.swagup.databinding.ActivityDressupBinding;

public class UserActivity extends AppCompatActivity {
    ActivityDressupBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDressupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if (intent != null){
            String message = intent.getStringExtra("name");
            int imageid = intent.getIntExtra("imageid", R.drawable.a);




        }
    }
}