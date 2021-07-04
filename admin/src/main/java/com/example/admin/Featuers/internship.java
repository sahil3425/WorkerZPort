package com.example.admin.Featuers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.admin.Models.InternshipModel;
import com.example.admin.R;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class internship extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText CompanyName, jobtype, Experience, skills, paid,Mail;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internship);
        init();
    }

    public void internship(View view) {
        InternshipModel developmentDetails=new InternshipModel(CompanyName.getText().toString(),jobtype.getText().toString(),Experience.getText().toString(),skills.getText().toString(),paid.getText().toString(),Mail.getText().toString());
        progressBar.setVisibility(View.VISIBLE);
        db.collection("Internships")
                .add(developmentDetails)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(internship.this, "Details Filled .",
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


    private void init() {
        CompanyName=findViewById(R.id.companyNameEdit);
        jobtype=findViewById(R.id.jobtypeName);
        Experience=findViewById(R.id.experience);
        skills=findViewById(R.id.skills);
        paid=findViewById(R.id.paid);
        Mail=findViewById(R.id.CompanyMailEdit1);
        progressBar = (ProgressBar) findViewById(R.id.spin_kit);
        Sprite doubleBounce = new Circle();
        progressBar.setIndeterminateDrawable(doubleBounce);
        progressBar.setVisibility(View.GONE);
    }
}