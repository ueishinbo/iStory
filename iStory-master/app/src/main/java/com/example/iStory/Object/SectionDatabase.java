package com.example.iStory.Object;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SectionDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;//the early version of database
    public static final String TABLE_NAME = "section";
    public static final String SECTION_ID = "section_id";
    public static final String AUTHOR_ID = "author_id";
    public static final String STORY_ID = "story_id";
    public static final String TAG = "tag";
    public static final String SECTION_HEADING="section_heading";
    public static final String SECTION_CONTENT="section_content";
    public static final String TIME = "time";
    public static final String LIKES="likes";
    public Context mContext;

    public SectionDatabase(Context context){
        super(context,TABLE_NAME,null,1);
        this.getWritableDatabase();
        mContext=context;
    }

    //initialization--first time the app created
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME
                + "("
                + SECTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + AUTHOR_ID + " LONG,"
                + STORY_ID + " LONG,"
                + TAG + " TEXT,"
                + SECTION_HEADING + " TEXT,"
                + SECTION_CONTENT + " TEXT,"
                + TIME + " TEXT,"
                + LIKES + " INTEGER DEFAULT 0)"
        );
    }

    //when the database is updated
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}