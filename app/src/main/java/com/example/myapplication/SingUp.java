package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.FirebaseDatabase;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.jetbrains.annotations.NotNull;

public class SingUp extends AppCompatActivity {

    TextInputEditText textInputEditTextFullname;
    TextInputEditText textInputEditTextUsername;
    TextInputEditText textInputEditTextPassword;
    TextInputEditText textInputEditTextEmail;
    Button buttonSingUp;
    TextView textViewLogin;
    ProgressBar progressBar;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        textInputEditTextFullname = findViewById(R.id.fullname);
        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.password);
        textInputEditTextEmail = findViewById(R.id.email);
        buttonSingUp = findViewById(R.id.buttonSignUp);
        textViewLogin = findViewById(R.id.loginText);
        progressBar = findViewById(R.id.progress);

        mAuth = FirebaseAuth.getInstance();

        buttonSingUp.setOnClickListener(view -> {
            createUser();
        });

        textViewLogin.setOnClickListener(v -> {
            startActivity(new Intent(SingUp.this, Login.class));
        });

    }

    private void createUser() {
        String username = textInputEditTextUsername.getText().toString();
        String fullname = textInputEditTextFullname.getText().toString();
        String password = textInputEditTextPassword.getText().toString();
        String email = textInputEditTextEmail.getText().toString();

        if(username.isEmpty()){
            textInputEditTextUsername.setError("Далбоеб напиши username нормально!");
            textInputEditTextUsername.requestFocus();
        }else if (fullname.isEmpty()){
            textInputEditTextFullname.setError("Далбоеб напиши fullname нормально!");
            textInputEditTextFullname.requestFocus();
        }else if (password.isEmpty()) {
            textInputEditTextPassword.setError("Далбоеб напиши password нормально!");
            textInputEditTextPassword.requestFocus();
        }else if (email.isEmpty()) {
            textInputEditTextEmail.setError("Далбоеб напиши email нормально!");
            textInputEditTextEmail.requestFocus();
        }else{
            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SingUp.this, "SingUp Success!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SingUp.this, Login.class));
                    }else{
                        Toast.makeText(SingUp.this, "Всё хуйня давай по новой!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}