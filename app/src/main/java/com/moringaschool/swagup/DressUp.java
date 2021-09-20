package com.moringaschool.swagup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.moringaschool.swagup.databinding.ActivityDressupBinding;
import com.moringaschool.swagup.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class DressUp extends AppCompatActivity {

    ActivityDressupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDressupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[] imageId = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g};
        String[] name = {"men","unisex","ladies","dope","business","swagger","men"};
        String[] message= {"casual","hangout","dinner dress","swag","official","boot up","casoffice"};

        ArrayList<User> userArrayList = new ArrayList<>();

        for(int i=0; i< imageId.length;i++){
            User user = new User(name[i],message[i],imageId[i]);
            userArrayList.add(user);
        }

        ListAdapter la = new listAdapter(DressUp.this,userArrayList);


        binding.listview.setAdapter(la);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(DressUp.this, ClothesActivity.class);
                i.putExtra("name",name[position]);
                i.putExtra("imageid",imageId[position]);
                startActivity(i);

            }
        });

    }
}