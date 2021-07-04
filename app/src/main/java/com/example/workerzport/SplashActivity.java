package com.example.workerzport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    final static String KEY_NAME="mypref";
    final static String google_verified="false";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences(KEY_NAME, MODE_PRIVATE);
        String google_verified1=sharedPreferences.getString(google_verified, "");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(google_verified1.equals("true")) {

                    Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent=new Intent(getApplicationContext(), BasicActivity.class);
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        },5000);

    }
}