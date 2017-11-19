package com.example.autobot.quicksolutionfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SignUp extends AppCompatActivity {
    private EditText UserName, Class, Gender, Email, Password, ConfirmPassword;
    private Button SignUp;
    private Firebase myMainDatabase;
    private Firebase myChildDatabase;
    private FirebaseAuth firebaseAuth;
    public static String UN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        Firebase.setAndroidContext(this);
        firebaseAuth = FirebaseAuth.getInstance();

        myMainDatabase = new Firebase("https://quicksolution-b9d5f.firebaseio.com/");


        UserName = (EditText)findViewById(R.id.editText4);
        Class = (EditText)findViewById(R.id.editText5);
        Gender = (EditText)findViewById(R.id.editText6);
        Email =(EditText)findViewById(R.id.editText7);
        Password = (EditText)findViewById(R.id.editText8);
        ConfirmPassword = (EditText)findViewById(R.id.editText9);
        SignUp = (Button)findViewById(R.id.button5);

        UN = UserName.getText().toString();

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = UserName.getText().toString();
                String clas = Class.getText().toString();
                String gender = Gender.getText().toString();
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                String confirmpassword = ConfirmPassword.getText().toString();
                myChildDatabase = new Firebase("https://quicksolution-b9d5f.firebaseio.com/Users" +":"+username);
                Firebase dbUserName = myChildDatabase.child("UserName");
                Firebase dbClass = myChildDatabase.child("Class");
                Firebase dbGender = myChildDatabase.child("Gender");
                Firebase dbEmail = myChildDatabase.child("Email");
                Firebase dbPassword = myChildDatabase.child("Password");
                if(Objects.equals(password, confirmpassword) )
                {
                    dbUserName.setValue(username);
                    dbClass.setValue(clas);
                    dbGender.setValue(gender);
                    dbEmail.setValue(email);
                    dbPassword.setValue(password);
                    registration();
                    refreshPage();
                    Intent SignUpIntent = new Intent(SignUp.this, MainActivity.class);
                    startActivity(SignUpIntent);


                }
                else
                {
                    Toast.makeText(
                            SignUp.this, "Password and Confirm Password is not matchhing",
                            Toast.LENGTH_LONG).show();
                }


            }
        });

    }
   public void registration()
   {

       firebaseAuth.createUserWithEmailAndPassword(Email.getText().toString(), Password.getText().toString())
               .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {


                       if (task.isSuccessful()) {
                           Toast.makeText(SignUp.this, "Registration successful", Toast.LENGTH_LONG).show();

                       }
                       else
                       {
                           Log.e("ERROR", task.getException().toString());
                           Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                       }
                   }
               });

  }



    public void refreshPage() {
        UserName.setText("");
        Gender.setText("");
        Class.setText("");
        Email.setText("");
        Password.setText("");
        ConfirmPassword.setText("");
        Toast.makeText(
                SignUp.this, "U have successfully Registerd",
                Toast.LENGTH_LONG).show();
    }




}

