package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class Login extends AppCompatActivity {
    TextInputEditText textInputEditTextEmail, textInputEditTextPassword;
    Button buttonLogin;
    TextView textViewSingUp;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        textInputEditTextEmail = findViewById(R.id.email);
        textInputEditTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewSingUp = findViewById(R.id.signUpText);
        progressBar = findViewById(R.id.progress);

        buttonLogin.setOnClickListener(view -> {
            LoginUser();
        });

        textViewSingUp.setOnClickListener(v -> {
            startActivity(new Intent(Login.this, SingUp.class));
        });
    }

    private void LoginUser() {
        String email = textInputEditTextEmail.getText().toString();
        String password = textInputEditTextPassword.getText().toString();

        if(email.isEmpty()){
            textInputEditTextEmail.setError("Далбоеб напиши email нормально!");
            textInputEditTextEmail.requestFocus();
        }else if (password.isEmpty()) {
            textInputEditTextPassword.setError("Далбоеб напиши password нормально!");
            textInputEditTextPassword.requestFocus();
        }else{
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Login.this, "Login Success!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, MainActivity.class));
                    }else{
                        Toast.makeText(Login.this, "Всё хуйня давай по новой!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}