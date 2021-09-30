package com.moringaschool.swagup;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.moringaschool.swagup.databinding.ActivityDressupBinding;
import com.moringaschool.swagup.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class DressUp extends AppCompatActivity {
    private Button btOpen;
    private ImageView imageView;
    ActivityDressupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDressupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btOpen = (Button) findViewById(R.id.btOpen);
        imageView = (ImageView) findViewById(R.id.image_view);


        if (ContextCompat.checkSelfPermission(DressUp.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(DressUp.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    },
                    100);
        }
        btOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);

            }
        });



        int[] picId = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g};
        String[] name = {"men","unisex","ladies","dope","business","swagger","men"};
        String[] message= {"casual","hangout","dinner dress","swag","official","boot up","casoffice"};

        ArrayList<User> userArrayList = new ArrayList<>();

        for(int i=0; i< picId.length;i++){
//            User user = new User(name[i],message[i],picId[i]);
//            userArrayList.add(user);
        }

        ListAdapter la = new listAdapter(DressUp.this,userArrayList);


        binding.listview.setAdapter(la);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(DressUp.this, ClothesActivity.class);
                i.putExtra("name",name[position]);
                i.putExtra("imageid",picId[position]);
                startActivity(i);

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            //Get Capture Image
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            //set capture Image to ImageView
            imageView.setImageBitmap(captureImage);
        }
    }
}

