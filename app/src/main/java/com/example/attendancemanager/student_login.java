package com.example.attendancemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class student_login  extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {




    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        drawerLayout= findViewById(R.id.drawer);
        toolbar=findViewById(R.id.toolbar);
        navigationView=findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen,R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.ViewAttendence:
                Toast.makeText(student_login.this,"ATTENDENCE GHE",Toast.LENGTH_SHORT).show();
                break;
            case R.id.defaulters:
                Toast.makeText(student_login.this,"ATTENDENCE BAGH",Toast.LENGTH_SHORT).show();
                break;
            case R.id.courses:
                Toast.makeText(student_login.this,"ZHALA",Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:

                Intent i=new Intent(student_login.this,MainActivity.class);
                startActivity(i);
                break;

        }
        return false;
    }

}
