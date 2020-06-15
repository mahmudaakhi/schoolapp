package com.example.schoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class loginrejected extends AppCompatActivity {
    Button registeragain;
    Button callnow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginrejected);
        registeragain=findViewById(R.id.regagain);
        callnow=findViewById(R.id.callnow);

        registeragain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(loginrejected.this,MainActivity.class);
                startActivity(intent);
            }
        });
        callnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+৮৮০১৮১৯২৭৯০২৫"));
                startActivity(intent);

            }
        });
    }
}
