package com.example.autobot.quicksolutionfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

import static com.example.autobot.quicksolutionfinal.SignUp.UN;

public class Profile extends AppCompatActivity {
    private TextView Name, Gender, Class, Email;
    private Firebase ShowID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
}

