package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MaterialButton btnLogin = findViewById(R.id.btnLogin);
        MaterialButton btnRegister = findViewById(R.id.btnRegister);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });
    btnRegister.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, Register.class));
        }
    });
    }

}