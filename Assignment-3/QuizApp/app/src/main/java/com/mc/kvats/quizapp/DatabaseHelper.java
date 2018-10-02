package com.mc.kvats.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "QuizApp.db";
    public static final String TABLE_NAME = "QuestionAnswer";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Question";
    public static final String COL_3 = "Answer"; // 0 if not answered, 1 if True and 2 if False answered.

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, Question TEXT, Answer INTEGER DEFAULT 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

//    public boolean insertData(String Q, int ans) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//    }
}
