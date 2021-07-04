package com.example.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.admin.Featuers.DesignActivity;
import com.example.admin.Featuers.Development;
import com.example.admin.Featuers.internship;
import com.example.admin.Login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Designer(View view) {
        startActivity(new Intent(MainActivity.this, DesignActivity.class));
    }

    public void Development(View view) {
        startActivity(new Intent(getApplicationContext(), Development.class));
    }

    public void GoIntern(View view) {
        startActivity(new Intent(getApplicationContext(), internship.class));

    }

    public void logout(View view) {
        Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}