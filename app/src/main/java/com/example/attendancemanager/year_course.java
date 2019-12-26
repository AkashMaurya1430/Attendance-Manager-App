package com.example.attendancemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class year_course extends AppCompatActivity {

    Spinner select_year, select_course;
    ArrayList<String> arrayList_select_year;
    ArrayAdapter<String> arrayAdapter_select_year;

    ArrayList<String> arrayList_SE, arrayList_TE, arrayList_BE;

    ArrayAdapter<String> arrayAdapter_select_course;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_course);

        select_year  = (Spinner) findViewById(R.id.select_year);
        select_course  = (Spinner) findViewById(R.id.select_course);

        arrayList_select_year = new ArrayList<>();
        arrayList_select_year.add("YEARS");
        arrayList_select_year.add("SE");
        arrayList_select_year.add("TE");
        arrayList_select_year.add("BE");


        arrayAdapter_select_year = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_select_year);

        select_year.setAdapter(arrayAdapter_select_year);


        arrayList_SE = new ArrayList<>();
        arrayList_SE.add("AM 3");
        arrayList_SE.add("PC");
        arrayList_SE.add("DSA");
        arrayList_SE.add("DMS");
        arrayList_SE.add("LD");
        arrayList_SE.add("JAVA");
        arrayList_SE.add("AM 4");
        arrayList_SE.add("WEB PROGRAMMING");
        arrayList_SE.add("INFORMATION THEORY AND CODING");
        arrayList_SE.add("COMPUTER NETWORKS");
        arrayList_SE.add("COAO");
        arrayList_SE.add("AUTOMATED THEORY");
        arrayList_SE.add("OS");


        arrayList_TE = new ArrayList<>();
        arrayList_TE.add("A");
        arrayList_TE.add("AB");
        arrayList_TE.add("AC");
        arrayList_TE.add("AD");
        arrayList_TE.add("AF");
        arrayList_TE.add("AH");
        arrayList_TE.add("AG");


        arrayList_BE = new ArrayList<>();
        arrayList_BE.add("XYZ");
        arrayList_BE.add("AB");
        arrayList_BE.add("AC");
        arrayList_BE.add("AD");
        arrayList_BE.add("AF");
        arrayList_BE.add("AH");
        arrayList_BE.add("AG");

        select_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if(position == 1){
                    arrayAdapter_select_course = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_SE);

                }

                if(position == 2){
                    arrayAdapter_select_course = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_TE);

                }

                if(position == 3){
                    arrayAdapter_select_course = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_BE);

                }

                select_course.setAdapter(arrayAdapter_select_course);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG).show();            }
        });



    }

    public void startAttendance(View view) {
        Intent intent = new Intent(year_course.this, Take_attendance.class);
        startActivity(intent);
    }
}
