package com.example.iStory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iStory.Database.DB;
import com.example.iStory.Object.User;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regisiter);
        Button regBtn = findViewById(R.id.register);
        Button cancelRegBtn = findViewById(R.id.cancelReg);
        EditText nameET = findViewById(R.id.userNameField);
        EditText passwordET = findViewById(R.id.passwordField);
        EditText confirmET = findViewById(R.id.confirmPasswordField);

        //commit register
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Boolean result = false;
                        String userId = DB.createUserId();
                        String name = nameET.getText().toString();
                        String password = passwordET.getText().toString();
                        String confirm = confirmET.getText().toString();
                        Looper.prepare();
                        if (password.equals(confirm) && !name.equals("") && !password.equals("")) {
                            User user = DB.getUserByName(name);
                            if (user == null) {
                                result = DB.createUser(userId, name, password);
                                SharedPreferences userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
                                SharedPreferences.Editor editor = userInfo.edit();
                                editor.putString("uuid", userId);
                                editor.commit();
                            }
                            if (result) {
                                Toast.makeText(RegisterActivity.this, "Register successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Username exists", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "The passwords entered are different", Toast.LENGTH_LONG).show();
                        }
                        Looper.loop();
                    }
                }).start();


            }
        });
        //cancel register
        cancelRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}