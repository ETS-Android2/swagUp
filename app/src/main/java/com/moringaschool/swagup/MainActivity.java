package com.moringaschool.swagup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mFindAttiresButton;

    //    public static final String TAG = MainActivity.class.getSimpleName();
    private EditText mAttireEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFindAttiresButton = (Button) findViewById(R.id.findAttiresButton);
        mFindAttiresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "FIND!", Toast.LENGTH_LONG).show();
                Intent register = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(register);
            }
        });
    }}