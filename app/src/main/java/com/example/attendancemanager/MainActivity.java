package com.example.attendancemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    private ProgressDialog progressDialog;

    private Button buttonlogin;
    EditText emailid,password,emailreset;
    Spinner spinner;
    CheckBox checkBox;
    FirebaseAuth firebaseAuth ;
    TextView forgot,register;
    private Dialog myDialog;
    private TextView textClose;
    private Button resetpassword;
    private SharedPreferences mPrefs;
    private FirebaseAuth.AuthStateListener authStateListener;
    private static final String PREFS_NAME="PrefsFIle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonlogin =(Button) findViewById(R.id.login);
        password=(EditText)findViewById(R.id.password);
        emailid=(EditText) findViewById(R.id.username);
        forgot=(TextView) findViewById(R.id.password_reset);
        register=(TextView) findViewById(R.id.logintext);
        spinner =(Spinner) findViewById(R.id.menu);
        myDialog= new Dialog(this);

        checkBox = (CheckBox) findViewById(R.id.checkboxrememberme);
        progressDialog =new ProgressDialog(this);

        mPrefs=getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        getPrefencesData();

        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(MainActivity.this,R.array.names,R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        firebaseAuth = firebaseAuth.getInstance();


        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Lemail=emailid.getText().toString().trim();
                String Lpassword=password.getText().toString().trim();
                final String item=spinner.getSelectedItem().toString();

                progressDialog.setMessage("LOGGING IN ");
                progressDialog.show();

                if(item.equals("USER TYPE"))
                {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Select A Valid User Type", Toast.LENGTH_SHORT).show();
                }

               else
                {
                    if(item.equals("TEACHER")){
                                firebaseAuth.signInWithEmailAndPassword(Lemail,Lpassword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            if (checkBox.isChecked()){
                                                Boolean boolisChecked = checkBox.isChecked();
                                                SharedPreferences.Editor editor=mPrefs.edit();
                                                editor.putString("pref_name",emailid.getText().toString());
                                                editor.putString("pref_pass",password.getText().toString());
                                                editor.putBoolean("pref_check",boolisChecked);
                                                editor.apply();
                                                Toast.makeText(getApplicationContext(),"SETTINGS HAVE BEEN SAVED",Toast.LENGTH_SHORT).show();


                                            }else{
                                                mPrefs.edit().clear().apply();
                                                emailid.getText().clear();
                                                password.getText().clear();
                                            }
                                            progressDialog.dismiss();
                                                Intent intent = new Intent(MainActivity.this, teacher_login.class);
                                                startActivity(intent);
                                            }
                                        else{
                                            progressDialog.dismiss();
                                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                                        }
                                        }
                            });
                            }
                            else if(item.equals("STUDENT")){
                                firebaseAuth.signInWithEmailAndPassword(Lemail,Lpassword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            if (checkBox.isChecked()){
                                                Boolean boolisChecked = checkBox.isChecked();
                                                SharedPreferences.Editor editor=mPrefs.edit();
                                                editor.putString("pref_name",emailid.getText().toString());
                                                editor.putString("pref_pass",password.getText().toString());
                                                editor.putBoolean("pref_check",boolisChecked);
                                                editor.apply();
                                                Toast.makeText(getApplicationContext(),"SETTINGS HAVE BEEN SAVED",Toast.LENGTH_SHORT).show();
                                            }else{
                                                mPrefs.edit().clear().apply();
                                                emailid.getText().clear();
                                                password.getText().clear();
                                            }
                                            progressDialog.dismiss();
                                            Intent intent = new Intent(MainActivity.this, student_login.class);
                                            startActivity(intent);
                                        }
                                        else{
                                            progressDialog.dismiss();
                                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });
                            }
                            else if(item.equals("ADMIN")){
                                firebaseAuth.signInWithEmailAndPassword(Lemail,Lpassword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            if (checkBox.isChecked()){
                                                Boolean boolisChecked = checkBox.isChecked();
                                                SharedPreferences.Editor editor=mPrefs.edit();
                                                editor.putString("pref_name",emailid.getText().toString());
                                                editor.putString("pref_pass",password.getText().toString());
                                                editor.putBoolean("pref_check",boolisChecked);
                                                editor.apply();
                                                Toast.makeText(getApplicationContext(),"SETTINGS HAVE BEEN SAVED",Toast.LENGTH_SHORT).show();


                                            }else{
                                                mPrefs.edit().clear().apply();
                                                emailid.getText().clear();
                                                password.getText().clear();
                                            }
                                            progressDialog.dismiss();
                                            Intent intent = new Intent(MainActivity.this, Admin.class);
                                            startActivity(intent);
                                        }
                                        else{
                                            progressDialog.dismiss();
                                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                            }

                }

            }
        });






    }

    private void getPrefencesData() {
        SharedPreferences sharedPreferences= getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        if(sharedPreferences.contains("pref_name")){
            String u = sharedPreferences.getString("pref_name","not found.");
            emailid.setText(u.toString());
        }
        if(sharedPreferences.contains("pref_pass")){
            String p = sharedPreferences.getString("pref_pass","not found");
            password.setText(p.toString());
        }
        if(sharedPreferences.contains("pref_check")){
            Boolean b = sharedPreferences.getBoolean("pref_check",false);
            checkBox.setChecked(b);
        }
    }

    public void registerUser(View view) {
        Intent intent= new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
    }

    public void resetPass(View view) {
        myDialog.setContentView(R.layout.password_popup);
        textClose=(TextView) myDialog.findViewById(R.id.textClose);
        resetpassword = (Button) myDialog.findViewById(R.id.button_reset);
        emailreset= (EditText) myDialog.findViewById(R.id.reset_passtext);
        textClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth = FirebaseAuth.getInstance();
                String useremail = emailreset.getText().toString().trim();


                if(useremail.equals("")){

                    Toast.makeText(MainActivity.this, "ENTER A EMAIL ID", Toast.LENGTH_SHORT).show();
                }else{

                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "PASSWORD RESET EMAIL SENT", Toast.LENGTH_SHORT).show();
                                myDialog.dismiss();
                            }
                            else{

                                Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}

