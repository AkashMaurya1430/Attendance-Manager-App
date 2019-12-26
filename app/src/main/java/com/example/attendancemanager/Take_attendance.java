package com.example.attendancemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Take_attendance extends AppCompatActivity {


TextView showvalue;
int prn=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);

        showvalue=(TextView) findViewById(R.id.prnupdate);


    }

    public void Present(View view) {
       prn++;
       showvalue.setText(Integer.toString(prn));




    }

    public void Absent(View view) {


        prn++;
        showvalue.setText(Integer.toString(prn));

    }
}
