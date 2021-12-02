package cn.edu.xjtlu.istory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.edu.xjtlu.istory.DB.DBUtils;
import cn.edu.xjtlu.istory.Object.User;

class CreateUserThread implements Runnable{
    boolean isCreate = false;
    private String userName;
    private int userID;
    private String password;
    public void setUser(String userName,int userID,String password){
        this.userName = userName;
        this.password = password;
        this.userID = userID;
    }
    @Override
    public void run() {
        Boolean createUser = DBUtils.createUser(userID,userName,password);
        if (createUser){
            isCreate = true;
        }
    }
}
//----------
class checkUserIsExistThread implements Runnable{
    boolean isExist = false;
    private String userName;

    public void setUserName(String userName){
        this.userName = userName;
    }
    @Override
    public void run() {
        User userObj = DBUtils.getUser(userName);
        if (userObj != null){
            isExist = true;
        }else {
            isExist = false;
        }
    }
}

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button regBtn = findViewById(R.id.register);
        Button cancelRegBtn = findViewById(R.id.cancelReg);
        EditText nameET = findViewById(R.id.userNameField);
        EditText passwordET = findViewById(R.id.passwordField);
        EditText confirmET = findViewById(R.id.confirmPasswordField);

        //commit register
        regBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //String userId = DBUtils.createUserId();
                int userId = 3;
                String name = nameET.getText().toString();
                String password = passwordET.getText().toString();
                String confirm = confirmET.getText().toString();
                //System.out.println("生成的userId是" +  userId);
                if(password.equals(confirm)){
                    checkUserIsExistThread checkUserT = new checkUserIsExistThread();
                    checkUserT.setUserName(name);
                    Thread thread = new Thread(checkUserT);
                    thread.start();
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (checkUserT.isExist){
                        Toast.makeText(RegisterActivity.this, "The user name already exists.", Toast.LENGTH_LONG).show();
                    }else {
                        CreateUserThread CUT = new CreateUserThread();
                        CUT.setUser(name,userId,password);
                        Thread thread1 = new Thread(CUT);
                        thread1.start();
                        try {
                            thread1.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (CUT.isCreate){
                            Toast.makeText(RegisterActivity.this, "Create Successfully", Toast.LENGTH_LONG).show();
                        }else {
                            System.out.println(" 创建失败");
                        }
                    }
                }else {
                    Toast.makeText(RegisterActivity.this, "The passwords entered are different", Toast.LENGTH_LONG).show();
                }
                /*new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Boolean result = false;

                        //Looper.prepare();
                        if (password.equals(confirm) && !name.equals("") && !password.equals("")) {
                            User user = DBUtils.getUser(name);
                            if (user == null) {
                                result = DBUtils.createUser(userId, name, password);
                                SharedPreferences userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
                                SharedPreferences.Editor editor = userInfo.edit();
                                editor.putString("uuid", userId);
                                editor.commit();
                            }
                            if (result) {
                                //Toast.makeText(RegisterActivity.this, "Register successfully", Toast.LENGTH_LONG).show();
                                System.out.println("插入成功");
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                System.out.println("插入失败");
                                //Toast.makeText(RegisterActivity.this, "Username exists", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            System.out.println("两次输入的密码不一致");
                            //Toast.makeText(RegisterActivity.this, "The passwords entered are different", Toast.LENGTH_LONG).show();
                        }
                        //Looper.loop();
                    }
                }).start();*/


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