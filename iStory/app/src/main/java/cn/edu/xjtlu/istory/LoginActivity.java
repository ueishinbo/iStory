package cn.edu.xjtlu.istory;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.xjtlu.istory.DB.DBUtils;
import cn.edu.xjtlu.istory.Object.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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
                /*SharedPreferences userInfo = getSharedPreferences("userInfo",MODE_PRIVATE);
                String userId = userInfo.getString("uuid",null);*/
                String userName = userNameET.getText().toString();
                String password = passwordET.getText().toString();
                //User userObj = DB.getUser(userId);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        User userObj = DBUtils.getUser(userName);
                        if (userObj != null){
                            if(userName.equals(userObj.getUserName())&&password.equals(userObj.getPassword())){
                                System.out.println("登录成功，用户名为：" + userObj.getUserName());
                            /* Looper.prepare();
                            Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_LONG).show();
                            Looper.loop();*/
                                Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                                startActivity(intent);
                            }else {
                                System.out.println("登录失败,密码错误");
                                //Toast.makeText(LoginActivity.this, "Wrong user name or password", Toast.LENGTH_LONG).show();
                            }
                        }else {
                            System.out.println("登录失败，无此账号");
                        }

                    }
                }).start();

            }
        });



    }
}