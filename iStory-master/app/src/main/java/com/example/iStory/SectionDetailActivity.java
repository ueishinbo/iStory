package com.example.iStory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.iStory.Object.Section;

public class SectionDetailActivity extends AppCompatActivity {

    private Button detailBtn;
    private Button postBtn;
    private ImageButton backBtn;
    private TextView section_detail_time;
    private TextView section_detail_author;

    Section new_section;
    //show
    private TextView section_heading;
    private TextView section_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_detail);
        detailBtn = findViewById(R.id.storyDetailBtn);
        postBtn = findViewById(R.id.postSectionBtn);
        backBtn=findViewById(R.id.backBtn);
        section_content=findViewById(R.id.sectionDetail_content);
        section_heading=findViewById(R.id.sectionDetail_heading);
        section_detail_time=findViewById(R.id.section_detail_time);
        section_detail_author=findViewById(R.id.section_detail_author);

        //receive intent from PostFragment
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            new_section=(Section) bundle.getSerializable("section");
        }
        section_content.setText(new_section.getSection_content());
        section_heading.setText(new_section.getSection_heading());
        section_detail_author.setText(String.valueOf(new_section.getAuthor_ID()));
        section_detail_time.setText(new_section.getTime());

        //故事详情
        detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SectionDetailActivity.this, StoryDetailActivity.class);
                startActivity(intent);
            }
        });
        //发布新故事,本次ID传输给下一个，作为pre_section_id
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SectionDetailActivity.this, PostSectionActivity.class);
//                long section_id= new_section.getSection_ID();
//                intent.putExtra("pre_section_id",section_id);
                startActivity(intent);
                finish();
            }
        });
        //返回到上一步()
        backBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){ finish();}
        });




    }


}