package com.example.iStory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PostSectionActivity extends AppCompatActivity {
    private Button postBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_section);
        postBtn = findViewById(R.id.postBtn3);
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PostSectionActivity.this,"Post successfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(PostSectionActivity.this, SectionDetailActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}