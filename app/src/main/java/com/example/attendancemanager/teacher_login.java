package com.example.attendancemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class teacher_login extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

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
            case R.id.TakeAttendence:
                Toast.makeText(teacher_login.this,"ATTENDENCE GHE",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(teacher_login.this,year_course.class);
                startActivity(intent);
                break;
            case R.id.ViewAttendence:
                Toast.makeText(teacher_login.this,"ATTENDENCE BAGH",Toast.LENGTH_SHORT).show();
                break;
            case R.id.upload:
                Toast.makeText(teacher_login.this,"ZHALA",Toast.LENGTH_SHORT).show();
                break;
            case R.id.Logout:
                Toast.makeText(teacher_login.this,"Logout",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(teacher_login.this,MainActivity.class);
                startActivity(i);
                break;

        }
        return false;
    }
}
