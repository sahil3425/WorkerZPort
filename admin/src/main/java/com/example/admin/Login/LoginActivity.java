package com.example.admin.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.MainActivity;
import com.example.admin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText userEmail1,userPassword;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Admin");
    String password="";
    String EmailID="",Password="";
    Button login;
    String email="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singIn();
            }
        });
    }
    private void init() {
        userEmail1=findViewById(R.id.userRegisterEmail);
        userPassword=findViewById(R.id.userRegisterPass);
        login =findViewById(R.id.button);
    }
    public void singIn() {
        email=userEmail1.getText().toString();
        password=userPassword.getText().toString();
        if(email.isEmpty() && password.isEmpty())
        {
            Toast.makeText(LoginActivity.this, "Fill The Details.",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        EmailID = ds.child("email").getValue().toString();
                        Password = ds.child("pass").getValue(String.class);
                        if (email.equals(EmailID) && password.equals(Password) ){
                            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }


                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("TAG", "Failed to read value.", error.toException());
                }
            });
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}