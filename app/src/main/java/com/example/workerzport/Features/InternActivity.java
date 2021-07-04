package com.example.workerzport.Features;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.workerzport.Adapters.AdapterIntern;
import com.example.workerzport.MainActivity;
import com.example.workerzport.Models.InternshipModel;
import com.example.workerzport.R;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class InternActivity extends AppCompatActivity {
RecyclerView recyclerView;
AdapterIntern adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("Internships");
    List<InternshipModel> helperList = new ArrayList<>();
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intern);
        recyclerView = findViewById(R.id.recycler);
        swipeRefreshLayout=findViewById(R.id.refresh);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        progressBar = (ProgressBar) findViewById(R.id.spin_kit);
        Sprite doubleBounce = new Circle();
        progressBar.setIndeterminateDrawable(doubleBounce);
        adapter = new AdapterIntern(getApplicationContext(), helperList);
        recyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.VISIBLE);
        DataRetrive();
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                Log.i("Refresh","done");
                helperList.clear();
                DataRetrive();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void DataRetrive() {
        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (QueryDocumentSnapshot queryDocumentSnapshot1 : queryDocumentSnapshots) {
                    InternshipModel helper=new InternshipModel();
                    helper.setCompanyName("Company Name : " + queryDocumentSnapshot1.getString("companyName"));
                    helper.setJobtype("Job Type : " + queryDocumentSnapshot1.getString("jobtype"));
                    helper.setExperience("Experience : " + queryDocumentSnapshot1.getString("experience"));
                    helper.setSkills("Skills : " + queryDocumentSnapshot1.getString("skills"));
                    helper.setPaid("Paid/Unpaid : " + queryDocumentSnapshot1.getString("paid"));
                    helper.setMail("Mail : " + queryDocumentSnapshot1.getString("mail"));
                    helperList.add(helper);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
        i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i1);
    }
}