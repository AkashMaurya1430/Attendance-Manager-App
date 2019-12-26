package com.example.attendancemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

    Spinner years,branches;
   private EditText name,prn,emailid,pass;
    Button register;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;



    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog =new ProgressDialog(this);


        years= (Spinner) findViewById(R.id.years);
        branches=(Spinner) findViewById(R.id.branches);
        name =(EditText) findViewById(R.id.name);
        prn = (EditText) findViewById(R.id.prn);
        emailid = (EditText) findViewById(R.id.email);
        pass=(EditText) findViewById(R.id.password);
        register= (Button) findViewById(R.id.register);

        awesomeValidation= new AwesomeValidation(ValidationStyle.UNDERLABEL);
        awesomeValidation.addValidation(this,R.id.name, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.prn,RegexTemplate.NOT_EMPTY,R.string.invalid_prn);
        awesomeValidation.addValidation(this,R.id.email, Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        awesomeValidation.addValidation(this,R.id.password, ".{6,}",R.string.invalid_pass);
        awesomeValidation.addValidation(this,R.id.years,RegexTemplate.NOT_EMPTY,R.string.invalid_year);
        awesomeValidation.addValidation(this,R.id.branches,RegexTemplate.NOT_EMPTY,R.string.invalid_branches);


        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.years));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        years.setAdapter(myAdapter);

        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.branches));
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branches.setAdapter(Adapter);

}

    public void loginUser(View view){
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void registerUser(View view) {
        String email= emailid.getText().toString().trim();
        String password= pass.getText().toString().trim();

        progressDialog.setMessage("REGISTERING USER");
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(awesomeValidation.validate()){
                            Toast.makeText(getApplicationContext(),"FORM VALIDATE SUCCESFULLY!",Toast.LENGTH_SHORT).show();
                            if (task.isSuccessful()){
                                Toast.makeText(Registration.this, "REGISTERED SUCCESFULLY!", Toast.LENGTH_SHORT).show();


                                progressDialog.dismiss();
                                Intent intent=new Intent(Registration.this,MainActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(Registration.this, "COULD NOT REGISTER! PLEASE TRY AGAIN!", Toast.LENGTH_SHORT).show();


                                progressDialog.dismiss();
                            }


                        }
                        else {
                            Toast.makeText(getApplicationContext(),"VALIDATION UNSUCCESFULL",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }



                    }
                });
    }





}


