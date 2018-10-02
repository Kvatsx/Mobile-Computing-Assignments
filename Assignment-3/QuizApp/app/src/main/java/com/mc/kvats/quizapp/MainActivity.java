package com.mc.kvats.quizapp;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Helper hp = Helper.getInstance();
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase(hp.DATABASE_NAME, MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS QuestionAnswer(ID INTEGER PRIMARY KEY AUTOINCREMENT, Question TEXT, Answer TEXT)");


        frameLayout = (FrameLayout) findViewById(R.id.fragment_container);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MainListFragment mainListFragment = new MainListFragment();
        ft.replace(R.id.fragment_container, mainListFragment);
        ft.commit();
    }


}
