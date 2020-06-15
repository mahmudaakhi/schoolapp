package com.example.schoolapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.util.Pair;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class firstpage extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;

    //Variables
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo, slogan;
    NavigationView navigationView;
    Toolbar toolbar;
    RelativeLayout relativeLayout;
    Menu menu;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //This Line will hide the status bar from the screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_firstpage);

        //Hooks
        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.welcome);
        slogan = findViewById(R.id.developer);



slogan.setMovementMethod(LinkMovementMethod.getInstance());

            //Animations
            topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anim);
            bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

            image.setAnimation(topAnim);
            logo.setAnimation(bottomAnim);
            slogan.setAnimation(bottomAnim);

                    //Call next screen
                    final Intent intent = new Intent(firstpage.this, secondpage.class);

                    Thread timer = new Thread() {
                        public void run() {
                            try {
                                sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
finally {
                                startActivity(intent);
                                finish();
                            }

                        }

                    };
                    timer.start();
        }
    }





