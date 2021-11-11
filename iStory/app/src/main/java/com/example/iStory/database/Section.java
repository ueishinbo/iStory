package com.example.iStory.database;

import java.io.Serializable;
//after Serializable, section object can be got by bundle and passed as a whole
public class Section implements Serializable {
    private long section_ID;
    private long author_ID;
    private long story_ID;
    private String tag;
    private String section_heading;
    private String section_content;
    private String time;
    private int likes;

    public Section(){

    }
    public Section(long author_ID, long story_ID, String tag, String section_heading, String section_content, String time, int likes) {

        this.author_ID = author_ID;
        this.story_ID = story_ID;
        this.tag = tag;
        this.section_heading = section_heading;
        this.section_content = section_content;
        this.time = time;
        this.likes=likes;
    }

    @Override
    public String toString() {
        return "Section{" +
                "section_ID=" + section_ID +
                ", author_ID=" + author_ID +
                ", story_ID=" + story_ID +
                ", tag='" + tag + '\'' +
                ", section_heading='" + section_heading + '\'' +
                ", section_content='" + section_content + '\'' +
                ", time=" + time +
                ", likes=" + likes +
                '}';
    }



    public long getSection_ID() {
        return section_ID;
    }

    public void setSection_ID(long section_ID) {
        this.section_ID = section_ID;
    }

    public long getAuthor_ID() {
        return author_ID;
    }

    public void setAuthor_ID(long author_ID) {
        this.author_ID = author_ID;
    }

    public long getStory_ID() {
        return story_ID;
    }

    public void setStory_ID(long story_ID) {
        this.story_ID = story_ID;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSection_heading() {
        return section_heading;
    }

    public void setSection_heading(String section_heading) {
        this.section_heading = section_heading;
    }

    public String getSection_content() {
        return section_content;
    }

    public void setSection_content(String section_content) {
        this.section_content = section_content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

}
