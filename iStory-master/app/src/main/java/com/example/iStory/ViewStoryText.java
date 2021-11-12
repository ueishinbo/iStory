package com.example.iStory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ViewStoryText extends AppCompatActivity {
    public ImageButton back;
    public TextView storyHeading;
    public TextView storyText;
    public TextView storyToWhere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewstorytext);
        back=findViewById(R.id.storytextback);
        storyHeading=findViewById(R.id.storyHeading);
        storyText=findViewById(R.id.storyText);
        storyToWhere=findViewById(R.id.storyToWhere);

        //返回到storyDetail.class
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ViewStoryText.this, StoryDetailActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}