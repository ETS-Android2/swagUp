package com.moringaschool.swagup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DressUp extends AppCompatActivity {
    private TextView mAttireTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dressup);
        mAttireTextView = (TextView) findViewById(R.id.attireTextView);
        Intent intent = getIntent();
        String attire = intent.getStringExtra("attire");
        mAttireTextView.setText("find your swag here: " + attire);
    }
}