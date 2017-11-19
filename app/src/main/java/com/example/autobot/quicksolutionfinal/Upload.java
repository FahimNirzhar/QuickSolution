package com.example.autobot.quicksolutionfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.snapshot.PriorityIndex;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static com.example.autobot.quicksolutionfinal.SignUp.UN;


public class Upload extends AppCompatActivity {
    private ImageButton SelectPhoto;
    private Button Submit;
    private EditText TitleOfPhoto, DescOfPhoto;

    private FirebaseAuth firebaseAuth;
    private StorageReference StorePhoto;
    private DatabaseReference mDatabaseUser;
    private Firebase mainDatabase;
    private Firebase chidDatabase;
    private Uri ImageUri = null;

    private FirebaseUser mCurrentUser;

    private static final int GALLERY_REQUEST =1;

    ProgressDialog mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        Firebase.setAndroidContext(this);
        firebaseAuth = FirebaseAuth.getInstance();
        mCurrentUser = firebaseAuth.getCurrentUser();

        SelectPhoto = (ImageButton) findViewById(R.id.imageButtonUp);
        Submit = (Button) findViewById(R.id.ButtonSubmit);

        TitleOfPhoto = (EditText)findViewById(R.id.txtTitle);
        DescOfPhoto = (EditText)findViewById(R.id.txtDesc);

        String Title = TitleOfPhoto.getText().toString();

        mainDatabase = new Firebase("https://quicksolution-b9d5f.firebaseio.com/");
        StorePhoto = FirebaseStorage.getInstance().getReference();

        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrentUser.getUid());


        mProgressBar = new ProgressDialog(this);

        SelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK);
                    galleryIntent.setType("image/*");
                    startActivityForResult(galleryIntent,GALLERY_REQUEST);
                }

        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPosting();

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK)
        {
            ImageUri = data.getData();
            SelectPhoto.setImageURI(ImageUri);
        }
    }
    private  void startPosting()
    {
        mProgressBar.setMessage("Posting.....");
        mProgressBar.show();

        final String title_val = TitleOfPhoto.getText().toString().trim();
        final String desc_val = DescOfPhoto.getText().toString().trim();

        if(!TextUtils.isEmpty(title_val) && ImageUri !=null){
            StorageReference filepath = StorePhoto.child("Problem_Images").child(ImageUri.getLastPathSegment());
            filepath.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    mProgressBar.dismiss();

                    chidDatabase = new Firebase("https://quicksolution-b9d5f.firebaseio.com/Users" +":"+title_val);

                    Firebase dbTitle = chidDatabase.child("title");
                    Firebase dbDesc = chidDatabase.child("desc");
                    Firebase dbImage = chidDatabase.child("image");
                    Firebase dbUID = chidDatabase.child("UID");

                    Firebase newPost = chidDatabase.push();
                    dbTitle.setValue(title_val);
                    dbDesc.setValue(desc_val);
                    dbImage.setValue(downloadUrl.toString());
                    dbUID.setValue(dbUID);

                  //  mProgressBar.dismiss();
                    Toast.makeText(Upload.this, "Uploaded", Toast.LENGTH_LONG).show();
                   intentChanger();
                }
            }) ;

        }
    }
    private  void intentChanger()
    {
//        TitleOfPhoto.setText("");
//        DescOfPhoto.setText("");
        Intent ic = new Intent(Upload.this, MainInterface.class);
        startActivity(ic);

    }



}
