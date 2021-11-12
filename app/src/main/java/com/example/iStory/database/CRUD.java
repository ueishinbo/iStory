package com.example.iStory.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class CRUD {
    SQLiteOpenHelper dbHandler;
    SQLiteDatabase db;

    public CRUD(Context context) {
        dbHandler = new SectionDatabase(context);
    }

    private static final String[] columns = {
            SectionDatabase.SECTION_ID,
            SectionDatabase.AUTHOR_ID,
            SectionDatabase.STORY_ID,
            SectionDatabase.TAG,
            SectionDatabase.SECTION_HEADING,
            SectionDatabase.SECTION_CONTENT,
            SectionDatabase.TIME,
            SectionDatabase.LIKES

    };

    public void open(){
        db = dbHandler.getWritableDatabase();
    }

    public void close(){
        dbHandler.close();
    }

    //把Section 加入到database里面
    public Section addSection(Section Section){
        open();
        //add a Section object to database
        ContentValues contentValues = new ContentValues();
        contentValues.put(SectionDatabase.AUTHOR_ID, Section.getAuthor_ID());
        contentValues.put(SectionDatabase.STORY_ID, Section.getStory_ID());
        contentValues.put(SectionDatabase.TAG, Section.getTag());
        contentValues.put(SectionDatabase.SECTION_HEADING, Section.getSection_heading());
        contentValues.put(SectionDatabase.SECTION_CONTENT, Section.getSection_content());
        contentValues.put(SectionDatabase.TIME, Section.getTime());
        contentValues.put(SectionDatabase.LIKES, Section.getLikes());
        long insertId = db.insert(SectionDatabase.TABLE_NAME, null, contentValues);
        Section.setSection_ID(insertId);

        close();
        return Section;
    }

    public Section getSection(long id){
        open();
        //get a Section from database using cursor index
        Cursor cursor = db.query(SectionDatabase.TABLE_NAME, columns, SectionDatabase.SECTION_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        Section e = new Section(cursor.getLong(1), cursor.getLong(2),cursor.getString(3), cursor.getString(4),cursor.getString(5), cursor.getString(6) ,cursor.getInt(7));
        close();
        return e;
    }

    //return整个list
    @SuppressLint("Range")
    public List<Section> getAllSections(){
        open();
        Cursor cursor = db.query(SectionDatabase.TABLE_NAME, columns, null, null, null, null, null);

        List<Section> Sections = new ArrayList<>();
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                Section Section = new Section();
                Section.setSection_ID(cursor.getLong(cursor.getColumnIndex(SectionDatabase.SECTION_ID)));
                Section.setAuthor_ID(cursor.getLong(cursor.getColumnIndex(SectionDatabase.AUTHOR_ID)));
                Section.setStory_ID(cursor.getLong(cursor.getColumnIndex(SectionDatabase.STORY_ID)));
                Section.setTag(cursor.getString(cursor.getColumnIndex(SectionDatabase.TAG)));
                Section.setSection_heading(cursor.getString(cursor.getColumnIndex(SectionDatabase.SECTION_HEADING)));
                Section.setSection_content(cursor.getString(cursor.getColumnIndex(SectionDatabase.SECTION_CONTENT)));
                Section.setTime(cursor.getString(cursor.getColumnIndex(SectionDatabase.TIME)));
                Section.setLikes(cursor.getInt(cursor.getColumnIndex(SectionDatabase.LIKES)));
                Sections.add(Section);
            }
        }
        close();
        return Sections;
    }

    public int updateSection(Section Section) {
        open();
        //update the info of an existing Section
        ContentValues values = new ContentValues();
        values.put(SectionDatabase.SECTION_ID, Section.getSection_ID());
        values.put(SectionDatabase.AUTHOR_ID, Section.getAuthor_ID());
        values.put(SectionDatabase.STORY_ID, Section.getStory_ID());
        values.put(SectionDatabase.TAG, Section.getTag());
        values.put(SectionDatabase.SECTION_HEADING, Section.getSection_heading());
        values.put(SectionDatabase.SECTION_CONTENT, Section.getSection_content());
        values.put(SectionDatabase.TIME, Section.getTime());
        values.put(SectionDatabase.LIKES, Section.getLikes());

        //updating row
        return db.update(SectionDatabase.TABLE_NAME, values,
                SectionDatabase.SECTION_ID + "=?", new String[] { String.valueOf(Section.getSection_ID())});
    }

    public void removeSection(Section Section){
        open();
        //remove a Section according to ID value
        db.delete(SectionDatabase.TABLE_NAME, SectionDatabase.SECTION_ID + "=" + Section.getSection_ID(), null);
        close();
    }

}

