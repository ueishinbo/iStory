package com.example.iStory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button toRegBtn = findViewById(R.id.toRegister);
        Button loginBtn = findViewById(R.id.login);
        //turn to register page
        toRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        //turn to main page
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });



    }
}