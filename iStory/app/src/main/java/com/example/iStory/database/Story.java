package com.example.iStory.database;

public class Story {

    private long story_ID;
    //shown in the picture
    private String heading;
    private String tag;


    public Story(long story_ID, String heading, String tag) {
        this.story_ID = story_ID;
        this.heading=heading;
        this.tag=tag;
    }

    @Override
    public String toString() {
        return "Story{" +
                "story_ID=" + story_ID +
                ", heading='" + heading + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getStory_ID() {
        return story_ID;
    }

    public void setStory_ID(long story_ID) {
        this.story_ID = story_ID;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }



}

