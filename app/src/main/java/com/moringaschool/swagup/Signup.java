package com.moringaschool.swagup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Signup extends AppCompatActivity {

    Spinner sp;
    boolean invalid = false;
    String Collector="";
    TextView txtalertName;
    EditText UserName,UserPassword,UserContact;
    Button SubmitSave;
    RadioButton Malebtn,Femalbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        sp=findViewById(R.id.SpCountry);
        UserName=findViewById(R.id.userName);
        UserPassword=findViewById(R.id.userPassword);
        UserContact=findViewById(R.id.userContact);
        txtalertName=findViewById(R.id.userAlert);
        Malebtn =findViewById(R.id.Male);
        Femalbtn=findViewById(R.id.Female);
        SubmitSave=findViewById(R.id.btnSubmit);
        SubmitSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = UserName.getText().toString();
                String Passcode = UserPassword.getText().toString();
                String contact = UserContact.getText().toString();

                if (name.isEmpty()) {
                    Toast.makeText(Signup.this, "Please fill the Username field", Toast.LENGTH_SHORT).show();
                } else if (name.equals("kepha") || name.equals("nyamweya")) {
                    invalid = true;
                    txtalertName.setText("Name Already exist");
                } else if (Passcode.isEmpty()) {
                    Toast.makeText(Signup.this, "Please fill the password field", Toast.LENGTH_SHORT).show();
                } else if (contact.isEmpty()) {
                    Toast.makeText(Signup.this, "Please fill the Contact field", Toast.LENGTH_SHORT).show();
                } else {

                    Collector += name + "\n";
                    Collector += Passcode + "\n";
                    Collector += contact + "\n";

                }
                Toast.makeText(Signup.this, "User Info \n:" + Collector, Toast.LENGTH_SHORT).show();
            }

            });


        List<String> categoryCountry=new ArrayList<>();
        categoryCountry.add("Select Country");
        categoryCountry.add("KENYA");
        categoryCountry.add("TANZANIA");
        categoryCountry.add("UGANDA");
        categoryCountry.add("RWANDA");
        categoryCountry.add("SOMALIA");
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,categoryCountry);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(arrayAdapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                if(parent.getItemAtPosition(position).equals("Select Country")){


                }
                else{
                    String item=parent.getItemAtPosition(position).toString();

                    Toast.makeText(Signup.this, "Selected Country: "+item, Toast.LENGTH_LONG).show();
                    Intent dressup = new Intent(Signup.this, DressUp.class);
                    startActivity(dressup);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {




            }
        });
    }
}