package com.example.iStory.database;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class SharedPre {

    //MXY: use SharedPreferences file to store tags, which have been set by developers. We can also save user info in this file for a easy fetch globally
    private final String PREFS_NAME="MyLightDatabase";

    public SharedPre(){

    }

    public void saveUserInfo(Context context){
        SharedPreferences tagInfo=context.getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        SharedPreferences.OnSharedPreferenceChangeListener changeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences preferences, String key) {
            }
        };
        tagInfo.registerOnSharedPreferenceChangeListener(changeListener);
        SharedPreferences.Editor editor = tagInfo.edit();//获取Editor
        //假设userID为1234
        editor.putLong("user", 1234);
        editor.commit();//提交修改
    }

    public long getUserInfo(Context context){
        SharedPreferences tagInfo=context.getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        Long user_ID=tagInfo.getLong("user",1);//读取tags_string
        return user_ID;

    }


    public void saveTagInfo(Context context){
        List<String> tags=new ArrayList<>();
        tags.add("Mystery");
        tags.add("Historical");
        tags.add("Love");
        tags.add("Horror");
        tags.add("Hot");
        String tags_string="";
        //MXY: 把tag里的放到tags——string进行储存。因为sharedPreference不好储存list
        for(int i=0;i<tags.size();i++){
            tags_string+=tags.get(i)+"_";
        }

        SharedPreferences tagInfo=context.getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        SharedPreferences.OnSharedPreferenceChangeListener changeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences preferences, String key) {
                //preferences被编辑的SharedPreferences实例
                //该SharedPreferences中被编辑的条目所对应的key
            }
        };
        tagInfo.registerOnSharedPreferenceChangeListener(changeListener);
        SharedPreferences.Editor editor = tagInfo.edit();//获取Editor
        //得到Editor后，写入需要保存的数据
        editor.putString("tags", tags_string);
        editor.commit();//提交修改

    }

    //MXY: 从lightdatabase里拿到tags
    public List<String> getTagInfo(Context context){
        SharedPreferences tagInfo=context.getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        String tags=tagInfo.getString("tags",null);//读取tags_string
        tags=tags.substring(0,tags.length()-1);//最后一个位是“_”
        String[] split_string=tags.split("_");
        List<String> tag_list=new ArrayList<String>();
        for(String s:split_string){
            tag_list.add(s);
        }
        Log.i("HomepageActivity", tags);
        return tag_list;

    }


}
