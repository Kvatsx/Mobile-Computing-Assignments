package com.mc.kvats.quizapp;

public class Helper {

    public static Helper hp;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "QuizApp.db";
    public static final String TABLE_NAME = "QuestionAnswer";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Question";
    public static final String COL_3 = "Answer"; // 0 if not answered, 1 if True and 2 if False answered.


    // QuestionAnswer Table
    public static int ID = 0;
    public static int QUESTION = 1;
    public static int ANSWER = 2;


    public static Helper getInstance() {
        if ( hp == null ) {
            hp = new Helper();
        }
        return hp;
    }
}
