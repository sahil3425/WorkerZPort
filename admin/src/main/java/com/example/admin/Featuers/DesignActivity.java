package com.example.admin.Featuers;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.admin.Models.DevelopmentDetails;
import com.example.admin.R;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DesignActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText CompanyName, jobtype, Experience, skills, location,Mail;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        init();
    }
    private void init() {
        CompanyName=findViewById(R.id.companyNameEdit);
        jobtype=findViewById(R.id.jobtypeName);
        Experience=findViewById(R.id.experience);
        skills=findViewById(R.id.skills);
        location=findViewById(R.id.location);
        Mail=findViewById(R.id.CompanyMailEdit1);
        progressBar = (ProgressBar) findViewById(R.id.spin_kit);
        Sprite doubleBounce = new Circle();
        progressBar.setIndeterminateDrawable(doubleBounce);
        progressBar.setVisibility(View.GONE);
    }
    public void Development(View view) {
        DevelopmentDetails developmentDetails=new DevelopmentDetails(CompanyName.getText().toString(),jobtype.getText().toString(),Experience.getText().toString(),skills.getText().toString(),location.getText().toString(),Mail.getText().toString());
        progressBar.setVisibility(View.VISIBLE);
        db.collection("Design")
                .add(developmentDetails)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(DesignActivity.this, "Details Filled .",
                                Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
}