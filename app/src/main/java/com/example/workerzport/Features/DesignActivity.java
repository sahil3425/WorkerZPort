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

import com.example.workerzport.Adapters.DevelopmentAdapter;
import com.example.workerzport.MainActivity;
import com.example.workerzport.Models.DevelopmentDetails;
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

public class DesignActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    DevelopmentAdapter adapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("Design");
    List<DevelopmentDetails> helperList = new ArrayList<>();
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        init();
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
                    DevelopmentDetails helper=new DevelopmentDetails();
                    helper.setCompanyName("Company Name : " + queryDocumentSnapshot1.getString("companyName"));
                    helper.setJobtype("Job Type : " + queryDocumentSnapshot1.getString("jobtype"));
                    helper.setExperience("Experience : " + queryDocumentSnapshot1.getString("experience"));
                    helper.setSkills("Skills : " + queryDocumentSnapshot1.getString("skills"));
                    helper.setLocation("Location : " + queryDocumentSnapshot1.getString("location"));
                    helper.setMail("Mail : " + queryDocumentSnapshot1.getString("mail"));
                    helperList.add(helper);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
    public void init(){
        recyclerView = findViewById(R.id.recyclerdesign);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        progressBar = (ProgressBar) findViewById(R.id.spin_kit);
        Sprite doubleBounce = new Circle();
        progressBar.setIndeterminateDrawable(doubleBounce);
        adapter = new DevelopmentAdapter(getApplicationContext(), helperList);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout=findViewById(R.id.refresh);
        progressBar.setVisibility(View.VISIBLE);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
        i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i1);
    }
}