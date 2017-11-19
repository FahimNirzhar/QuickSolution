package com.example.autobot.quicksolutionfinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth Auth;
    private FirebaseAuth.AuthStateListener mAuthlistener;

    public static EditText UserName;
    private EditText Password;
    private Button Login, SignUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        Auth = FirebaseAuth.getInstance();

        UserName = (EditText)findViewById(R.id.editText2);
        Password = (EditText)findViewById(R.id.editText3);
        Login = (Button) findViewById(R.id.button3);
        SignUpButton = (Button) findViewById(R.id.button4);
        mAuthlistener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null)
                {
                    Toast.makeText(MainActivity.this, "Signed in Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,MainInterface.class));

                }
//                else
//                {
//                    Toast.makeText(MainActivity.this, "Signed in Failed", Toast.LENGTH_SHORT).show();
//                }

            }
        };

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StartSignIn();
            }
        });
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSignUp = new Intent(MainActivity.this, SignUp.class);
                startActivity(intentSignUp);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();

        Auth.addAuthStateListener(mAuthlistener);
    }

    private void StartSignIn()
    {
        String uname = UserName.getText().toString();
        String pass = Password.getText().toString();
        if(TextUtils.isEmpty(uname) || TextUtils.isEmpty(pass) )
        {
            Toast.makeText(MainActivity.this, "Fields are empty",Toast.LENGTH_LONG).show();
        }else
        {
            Auth.signInWithEmailAndPassword(uname,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(!task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this, "Sign In Problem",Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                       // Toast.makeText(MainActivity.this, "Signed in Successfully", Toast.LENGTH_SHORT).show();
                        Intent SuccessIntent = new Intent(MainActivity.this, MainInterface.class);
                        startActivity(SuccessIntent);

                    }

                }
            });
        }

    }
}
