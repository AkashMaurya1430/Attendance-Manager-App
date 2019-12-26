package com.example.attendancemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class password_activity extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    private EditText password_email;
    private Button reset_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_activity);

        password_email = (EditText) findViewById(R.id.email_password);
        reset_pass = (Button) findViewById(R.id.reset_password);
        firebaseAuth = FirebaseAuth.getInstance();

        reset_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = password_email.getText().toString().trim();


                if(useremail.equals("")){

                    Toast.makeText(password_activity.this, "ENTER A EMAIL ID", Toast.LENGTH_SHORT).show();
                }else{

                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(password_activity.this, "PASSWORD RESET EMAIL SENT", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(password_activity.this, MainActivity.class));
                            }
                            else{

                                Toast.makeText(password_activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }

            }
        });
    }
}
