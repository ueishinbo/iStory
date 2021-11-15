package cn.edu.xjtlu.istory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PersonalSettingActivity extends AppCompatActivity {
    private Button saveBtn;
    private Button logOutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setting);
        saveBtn = findViewById(R.id.saveBtn);
        logOutBtn = findViewById(R.id.logOutBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(PersonalSettingActivity.this,HomePageActivity.class);
                startActivity(intent);
                finish();
            }
        });
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(PersonalSettingActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}