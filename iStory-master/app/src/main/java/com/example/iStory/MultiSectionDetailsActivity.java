package com.example.iStory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.iStory.Object.Section;

import java.util.ArrayList;
import java.util.List;

public class MultiSectionDetailsActivity extends AppCompatActivity {
    private RecyclerView nextSection;
    private RecyclerviewAdapter recyclerviewAdapter;
    private Button storyDetailBtn;
    private Button postBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_section_details);
        nextSection = findViewById(R.id.nextSectionRecyclerView);
        List<Section> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Section(12,34,"string","string","string","string",98));
        }
        recyclerviewAdapter = new RecyclerviewAdapter(this, list);
        nextSection.setLayoutManager(new LinearLayoutManager(this));
        nextSection.setAdapter(recyclerviewAdapter);
        storyDetailBtn = findViewById(R.id.storyDetailBtn2);
        storyDetailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MultiSectionDetailsActivity.this, StoryDetailActivity.class);
                startActivity(intent);
            }
        });
        postBtn = findViewById(R.id.postBtn2);
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MultiSectionDetailsActivity.this, PostSectionActivity.class);
                startActivity(intent);
            }
        });
    }
}