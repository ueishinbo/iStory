package com.example.iStory.database;

import java.util.Arrays;

public class User {


    private long user_ID;
    private String user_name;
    private String slogan;
    private long[] creation_story_list;
    private long[] creation_section_list;
    private long[] collectioin_collectioin_story_list;
    private long[] collectioin_section_list;
    private String[] tags;


    public User(long user_ID, String user_name, String slogan, long[] creation_story_list, long[] creation_section_list, long[] collectioin_collectioin_story_list, long[] collectioin_section_list, String[] tags) {
        this.user_ID = user_ID;
        this.user_name = user_name;
        this.slogan = slogan;
        this.creation_story_list = creation_story_list;
        this.creation_section_list = creation_section_list;
        this.collectioin_collectioin_story_list = collectioin_collectioin_story_list;
        this.collectioin_section_list = collectioin_section_list;
        this.tags = tags;
    }




    @Override
    public String toString() {
        return "User{" +
                "user_ID=" + user_ID +
                ", user_name='" + user_name + '\'' +
                ", slogan='" + slogan + '\'' +
                ", creation_story_list=" + Arrays.toString(creation_story_list) +
                ", creation_section_list=" + Arrays.toString(creation_section_list) +
                ", collectioin_collectioin_story_list=" + Arrays.toString(collectioin_collectioin_story_list) +
                ", collectioin_section_list=" + Arrays.toString(collectioin_section_list) +
                ", tags=" + Arrays.toString(tags) +
                '}';
    }




    public long[] getCollectioin_collectioin_story_list() {
        return collectioin_collectioin_story_list;
    }

    public void setCollectioin_collectioin_story_list(long[] collectioin_collectioin_story_list) {
        this.collectioin_collectioin_story_list = collectioin_collectioin_story_list;
    }



    public long getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(long user_ID) {
        this.user_ID = user_ID;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public long[] getCreation_story_list() {
        return creation_story_list;
    }

    public void setCreation_story_list(long[] creation_story_list) {
        this.creation_story_list = creation_story_list;
    }

    public long[] getCreation_section_list() {
        return creation_section_list;
    }

    public void setCreation_section_list(long[] creation_section_list) {
        this.creation_section_list = creation_section_list;
    }

    public long[] getCollectioin_section_list() {
        return collectioin_section_list;
    }

    public void setCollectioin_section_list(long[] collectioin_section_list) {
        this.collectioin_section_list = collectioin_section_list;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }



}
