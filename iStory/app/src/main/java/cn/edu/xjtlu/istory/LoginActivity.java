package cn.edu.xjtlu.istory;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import cn.edu.xjtlu.istory.DB.DB;
import cn.edu.xjtlu.istory.DB.DBUtils;
import cn.edu.xjtlu.istory.Object.User;

class DBThread implements Runnable{
    boolean isExist = false;

    private String userName;
    private String password;

    public void setUser(String userName,String password){
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void run() {
        User userObj = DBUtils.getUser(userName);
        if (userObj != null){
            System.out.println("数据库中的用户名为:"+ userObj.getUserName() + "," + "数据库中的密码为:" + userObj.getPassword());
            if(userName.equals(userObj.getUserName())&&password.equals(userObj.getPassword())){
                System.out.println("验证成功");
                isExist = true;
            }else {
                isExist = false;
            }
        }else {
            isExist = false;
        }
    }
}

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

                //verify the Username and Password
                DBThread DBT = new DBThread();
                DBT.setUser(userName,password);
                Thread thread = new Thread(DBT);
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (DBT.isExist){
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
