package com.example.iStory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.iStory.Object.Section;

public class StoryDetailActivity extends AppCompatActivity {
    ImageButton backBtn;
    public Button viewAllText;
    public Button viewThisSection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
        viewAllText=findViewById(R.id.storyDetails_viewAllText);
        viewThisSection=findViewById(R.id.storyDetails_viewThisSection);
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StoryDetailActivity.this, HomePageActivity.class);
                startActivity(intent);

            }
        });

        //直接看全文
        viewAllText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StoryDetailActivity.this, ViewStoryText.class);
                startActivity(intent);
            }
        });
        //有一个越堆越多的问题从树里选-->Sectiondetails-->树-->...

        viewThisSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //参考postFragment跳转到SectionDetailActivity的传递值，这里只是一个预设

                Section section=new Section(12,34,"Horror","just a test","refer to PostFragment","00",0);
                Intent intent = new Intent(StoryDetailActivity.this, SectionDetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("section",section);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}