package com.example.iStory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iStory.Database.DB;
import com.example.iStory.Object.CRUD;
import com.example.iStory.Object.Section;
import com.example.iStory.Object.User;

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
        EditText userNameET = findViewById(R.id.userName);
        EditText passwordET = findViewById(R.id.password);
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
                SharedPreferences userInfo = getSharedPreferences("userInfo",MODE_PRIVATE);
                String userId = userInfo.getString("uuid",null);
                String userName = userNameET.getText().toString();
                String password = passwordET.getText().toString();
                User userObj = DB.getUser(userId);
                if(userName.equals(userObj.getUserName())&&password.equals(userObj.getPassword())){
                    Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this, "Wrong user name or password", Toast.LENGTH_LONG).show();
                }

            }
        });



    }
}