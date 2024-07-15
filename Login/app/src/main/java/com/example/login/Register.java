package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    EditText signupname, signupemail, signupcontact, signuppass;
    TextView logingredirectText;
    Button signupbutton;
    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signupname = findViewById(R.id.signup_name);
        signupemail = findViewById(R.id.signup_email);
        signupcontact = findViewById(R.id.signup_contact);
        signuppass = findViewById(R.id.signup_pass);
        signupbutton = findViewById(R.id.signup_button);
        logingredirectText = findViewById(R.id.loginReddirect);
        //Firebase.initializeApp(this);


        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("user");

                String name = signupname.getText().toString();
                String email = signupemail.getText().toString();
                String contact = signupcontact.getText().toString();
                String password = signuppass.getText().toString();

                Helper helper = new Helper(name,email,contact,password);
                reference.child(name).setValue(helper);

                Toast.makeText(Register.this, "You have signup Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
        logingredirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }
}