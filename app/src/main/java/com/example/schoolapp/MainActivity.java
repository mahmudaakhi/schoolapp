package com.example.schoolapp;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private CircleImageView ProfileImage;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;

    private EditText name,phone,fathername,roll;
    private ProgressBar loadingProgress;
    private Button regBtn;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phonenumber);
        fathername = findViewById(R.id.fathername);
        roll = findViewById(R.id.roll);
        regBtn = findViewById(R.id.regbutton);

        mAuth = FirebaseAuth.getInstance();
        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Class_Name, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regBtn();
                User data = new User();
                String key = myRef.push().getKey();

                myRef.child(key).setValue(data);
                Toast.makeText(getApplicationContext(),"Data Stored",Toast.LENGTH_SHORT).show();
                name.setText("");
                phone.setText("");
                roll.setText("");
                fathername.setText("");

            }
        });
    }
    private void regBtn() {
        final String name1 = name.getText().toString();
        final String phone1 = phone.getText().toString();
        final String fathername1 = fathername.getText().toString();
        final String roll1 = roll.getText().toString();
        if (TextUtils.isEmpty(name1)){
            Toast.makeText(this,"please write your name",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phone1)){
            Toast.makeText(this,"please write your phone number",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(fathername1)){
            Toast.makeText(this,"please write your father name",Toast.LENGTH_SHORT).show();
        }
        else{
            validatePhoneNumber(name1,phone1,fathername1,roll1);
        }

    }

    private void validatePhoneNumber(String phone,String name, String fathername,String roll) {

                                        final DatabaseReference RootRef;
                                        RootRef= FirebaseDatabase.getInstance().getReference();
                                        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (!(dataSnapshot.child("Users").child(phone).exists())){
                                                    HashMap<String,Object> userdatMap=new HashMap<>();
                                                    userdatMap.put("phone",phone);
                                                    userdatMap.put("roll",roll);
                                                    userdatMap.put("Father Name",fathername);

                                                    userdatMap.put("name",name);
                                                    RootRef.child("Users").child(phone).updateChildren(userdatMap)
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if (task.isComplete()){
                                                                        Toast.makeText(MainActivity.this," ",Toast.LENGTH_SHORT).show();
                                                                        Intent intent=new Intent(MainActivity.this,LoginPending.class);
                                                                        startActivity(intent);
                                                                    }
                                                                    if (task.isCanceled()){
                                                                        Toast.makeText(MainActivity.this,"",Toast.LENGTH_SHORT).show();
                                                                        Intent intent=new Intent(MainActivity.this,loginrejected.class);
                                                                        startActivity(intent);
                                                                    }
                                                                    if (task.isSuccessful()){
                                                                        Toast.makeText(MainActivity.this,"",Toast.LENGTH_SHORT).show();
                                                                        Intent intent=new Intent(MainActivity.this,regicomplete.class);
                                                                        startActivity(intent);
                                                                    }

                                                                }
                                                            });


                                                }
                                               /* else{
                                                    Toast.makeText(RegisterActivity.this,"This" + phone + "already exists",Toast.LENGTH_SHORT).show();
                                                    loadingBar.dismiss();
                                                    Toast.makeText(RegisterActivity.this,"please try again using another phone number",Toast.LENGTH_SHORT).show();
                                                    Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                                                    startActivity(intent);
                                                }*/

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
        ProfileImage = (CircleImageView) findViewById(R.id.profile_image);
        ProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Sellect Picture"), PICK_IMAGE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                ProfileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

