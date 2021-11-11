package com.example.iStory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.iStory.database.CRUD;
import com.example.iStory.database.Section;

import java.util.List;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //每次运行都要初始化一下数据库，好看到底有没有更新之类的
        CRUD op=new CRUD(getApplicationContext());
        List<Section> list=op.getAllSections();
        for(Section s:list){
            op.removeSection(s);
        }


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