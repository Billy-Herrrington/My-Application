package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Login extends AppCompatActivity {
    TextInputEditText textInputEditTextUsername, textInputEditTextPassword;
    Button buttonLogin, checkBut;
    TextView textViewSingUp;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputEditTextPassword = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewSingUp = findViewById(R.id.signUpText);
        progressBar = findViewById(R.id.progress);
        checkBut = findViewById(R.id.checkBut);

        checkBut.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        });

        textViewSingUp.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SingUp.class);
            startActivity(intent);
            finish();
        });
        buttonLogin.setOnClickListener(v -> {
            final String username, password;
            username = String.valueOf(textInputEditTextUsername.getText());
            password = String.valueOf(textInputEditTextPassword.getText());

            if (!username.equals("") && !password.equals("")) {
                progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.post(() -> {
                    String[] field = new String[2];
                    field[0] = "username";
                    field[1] = "password";
                    String[] data = new String[2];
                    data[0] = username;
                    data[1] = password;
                    PutData putData = new PutData("http://192.168.0.119/LoginRegister/login.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBar.setVisibility(View.GONE);
                            String result = putData.getResult();
                            if (result.equals("Login Success")) {
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
            else {
                Toast.makeText(getApplicationContext(), "All fields reguired", Toast.LENGTH_SHORT).show();
            }
        });
    }
}