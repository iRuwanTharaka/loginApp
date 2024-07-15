    package com.example.login;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.google.firebase.Firebase;
    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.Query;
    import com.google.firebase.database.ValueEventListener;

    import java.util.Objects;

    public class Login extends AppCompatActivity {

        EditText loginUsername, loginPass;
        Button loginbutton;
        TextView signupRedirectText;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            loginUsername = findViewById(R.id.login_username);
            loginPass = findViewById(R.id.login_pass);
            signupRedirectText = findViewById(R.id.signupRedirect);
            loginbutton = findViewById(R.id.login_button);

            loginbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!validatePassword() | !validateUsername()){

                    } else {
                        checkUser();
                    }
                }
            });
            signupRedirectText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, Register.class);
                    startActivity(intent);
                }
            });

        }
        public Boolean validateUsername(){
            String val = loginUsername.getText().toString();
            if (val.isEmpty()){
                loginUsername.setError("Email can not be empty.");
                return false;
            } else {
                loginUsername.setError(null);
                return true;
            }
        }

        public Boolean validatePassword(){
            String val = loginPass.getText().toString();
            if (val.isEmpty()){
                loginPass.setError("Password can not be empty.");
                return false;
            } else {
                loginPass.setError(null);
                return true;
            }
        }

        public void checkUser(){
            String email = loginUsername.getText().toString().trim();
            String password = loginPass.getText().toString().trim();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("email");
            Query checkUserDatabase = reference.orderByChild(email).equalTo(email);

            checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        loginUsername.setError(null);
                        String passwordFromDB = snapshot.child(email).child("password").getValue(String.class);

                        if (!Objects.equals(passwordFromDB, password)){
                            loginUsername.setError(null);
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            loginPass.setError("Invalid Password");
                            loginPass.requestFocus();

                        }
                    } else {
                        loginUsername.setError("User does not exist.");
                        loginUsername.requestFocus();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


    }