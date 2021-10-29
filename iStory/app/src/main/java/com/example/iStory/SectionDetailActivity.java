package com.example.iStory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SectionDetailActivity extends AppCompatActivity {
    private Button preBtn;
    private Button nextBtn;
    private Button detailBtn;
    private Button postBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_detail);
        preBtn = findViewById(R.id.preBtn);
        nextBtn = findViewById(R.id.nextBtn);
        detailBtn = findViewById(R.id.storyDetailBtn);
        postBtn = findViewById(R.id.postSectionBtn);
        //上一个
        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SectionDetailActivity.this, SectionDetailActivity.class);
                startActivity(intent);
            }
        });
        //下一个
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //两种情况，高亮sectionDetail,现在是没高亮的multiSectionDetails
                Intent intent = new Intent(SectionDetailActivity.this, MultiSectionDetailsActivity.class);
                startActivity(intent);
            }
        });
        //故事详情
        detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SectionDetailActivity.this, StoryDetailActivity.class);
                startActivity(intent);
            }
        });
        //发布新故事
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SectionDetailActivity.this, PostSectionActivity.class);
                startActivity(intent);
            }
        });
    }
}