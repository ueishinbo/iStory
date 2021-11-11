package com.example.iStory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.iStory.database.CRUD;
import com.example.iStory.database.Section;
import com.example.iStory.database.SharedPre;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostSectionActivity extends AppCompatActivity {
    List<String> tags;
    long user_ID;
    ArrayAdapter<String> tag_adapter;
    SharedPre littleGetter=new SharedPre();
    private Button postBtn;
    Spinner tagSpinner;//tag
    EditText sectionHeadingpost;
    EditText sectionContentpost;

    int debug_helper;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post_section);
        //read info about tags
        littleGetter.saveTagInfo(this);
        tags=littleGetter.getTagInfo(this);
        //read info about users
        littleGetter.saveUserInfo(this);
        user_ID=littleGetter.getUserInfo(this);

        tagSpinner=findViewById(R.id.sectiontagspinner);
        tag_adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,tags);
        tag_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tagSpinner.setAdapter(tag_adapter);

        sectionHeadingpost=findViewById(R.id.sectionHeadingpost);
        sectionContentpost=findViewById(R.id.sectionContentpost);postBtn = findViewById(R.id.postBtn3);


        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add one section
                Section new_section=new Section(user_ID,34,tagSpinner.getSelectedItem().toString(),sectionHeadingpost.getText().toString(),sectionContentpost.getText().toString(),dateToStr(),0);
                CRUD op=new CRUD(getApplicationContext());
                op.addSection(new_section);

                List<Section> allSection=op.getAllSections();
                for(Section s:allSection){
                    debug_helper++;
                }
                op.close();

                //pass detail to next node
                Intent intent = new Intent(PostSectionActivity.this, SectionDetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("section",new_section);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });


    }

    //get time
    public String dateToStr(){
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}