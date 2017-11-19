package com.example.nirzhar.quicksolutionfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

public class MainInterface extends AppCompatActivity {
    private Button home, upload, signOut , calculator;
    private FirebaseAuth mAuth;

    private Firebase ShowValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);


        Firebase.setAndroidContext(this);
        mAuth = FirebaseAuth.getInstance();




        home = (Button)findViewById(R.id.BtnHome);
        upload = (Button)findViewById(R.id.BtnUpload);
        signOut = (Button) findViewById(R.id.BtnSignOut);
       // profile = (Button) findViewById(R.id.BtnProfile);
        calculator = (Button)findViewById(R.id.BtnCalculator);




        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent HomeIntent = new Intent(MainInterface.this, Home.class);
                startActivity(HomeIntent);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent UploadIntent = new Intent(MainInterface.this, Upload.class);
                startActivity(UploadIntent);
            }
        });
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainInterface.this, MainActivity.class));
            }
        });
        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calcintent = new Intent(MainInterface.this, Calculator.class );
                startActivity(calcintent);
            }
        });



    }


}
