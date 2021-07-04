package com.example.workerzport;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workerzport.Adapters.Adapter;
import com.example.workerzport.Features.About;
import com.example.workerzport.Features.DesignActivity;
import com.example.workerzport.Features.InternActivity;
import com.example.workerzport.Features.development;
import com.example.workerzport.Features.profile;
import com.example.workerzport.Models.slideImage;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    NavigationView nav;
   SearchView searchView;
    String searchText;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    DatabaseReference myRef;
    Toolbar toolbar;
    Adapter adapter;
    List<slideImage> helperList = new ArrayList<>();
    RecyclerView recyclerView;
    public static Boolean cardBoolean=false;
    final static String KEY_NAME="mypref";
    final static String google_verified="false";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               if(searchText.toLowerCase().contains("development") ||searchText.toLowerCase().contains("native") || searchText.toLowerCase().contains("developer")){
                   startActivity(new Intent(MainActivity.this, development.class));
               }
              else if(searchText.toLowerCase().contains("design") || searchText.toLowerCase().contains("uiux")){
                   startActivity(new Intent(MainActivity.this, development.class));
               }
              else if(searchText.toLowerCase().contains("internship")){
                   startActivity(new Intent(MainActivity.this, InternActivity.class));
               }
               else{
                   Toast.makeText(getApplicationContext(), "Sorry No Job Available of this type", Toast.LENGTH_LONG).show();
               }

         return true;
           }

           @Override
           public boolean onQueryTextChange(String newText) {
               searchText=newText;
               return true;
           }
       });
    

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    slideImage helper = new slideImage();
                    String imageURL = dataSnapshot.getValue().toString();
                    helper.setImage(imageURL);
                    helperList.add(helper);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_home:
                nav.setCheckedItem(R.id.menu_home);
                Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i1);
                finishAffinity();
                break;

            case R.id.menu_call:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+91-8168196670"));
                startActivity(intent);
                nav.setCheckedItem(R.id.menu_call);
                break;

            case R.id.menu_about:
                Toast.makeText(getApplicationContext(), "About Section", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, About.class));
                nav.setCheckedItem(R.id.menu_about);
                break;

            case R.id.menu_logout:
                Log.i("Tag","done");
                sharedPreferences=getSharedPreferences(KEY_NAME,MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(google_verified,"false");
                editor.apply();
                mAuth.signOut();
                Intent i = new Intent(MainActivity.this, BasicActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finishAffinity();
                Log.i("Tag","done");
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void init() {
        toolbar = findViewById(R.id.toolbar);
        searchView=findViewById(R.id.searchBar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.event_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new Adapter(getApplicationContext(), helperList);
        recyclerView.setAdapter(adapter);
        nav = findViewById(R.id.navmenu);
        nav.bringToFront();
        drawerLayout = findViewById(R.id.drawer);
        mAuth = FirebaseAuth.getInstance();
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("slider");
        nav.setNavigationItemSelectedListener(this);
    }

    public void viewProfile(View view) {
        Intent i2 = new Intent(getApplicationContext(), profile.class);
        startActivity(i2);
    }

    public void Designer(View view) {
        cardBoolean=true;
        startActivity(new Intent(this, DesignActivity.class));
    }

    public void Development(View view) {
        cardBoolean=false;
        startActivity(new Intent(this,development.class));
    }

    public void GoIntern(View view) {
        startActivity(new Intent(this, InternActivity.class));
    }
}