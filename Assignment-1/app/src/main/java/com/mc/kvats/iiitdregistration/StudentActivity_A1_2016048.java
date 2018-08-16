package com.mc.kvats.iiitdregistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class StudentActivity_A1_2016048 extends AppCompatActivity {
    public static final String TAG = "StudentActiv_A1_2016048";
    public static String CurrentState;

    TextView tvName, tvRollNumber, tvBranch, tvCourse1, tvCourse2, tvCourse3, tvCourse4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

//        getActionBar().setDisplayHomeAsUpEnabled(true);

//        Log.d(TAG, "Layout Set");
        Intent i = getIntent();
        Student_A1_2016048 student = (Student_A1_2016048)i.getSerializableExtra("studentObject");
//        Log.d(TAG, "Student_A1_2016048 Object Loaded");

        tvName = (TextView) findViewById(R.id.name);
        tvBranch = (TextView) findViewById(R.id.branch);
        tvRollNumber = (TextView) findViewById(R.id.rollNumber);
        tvCourse1 = (TextView) findViewById(R.id.course1);
        tvCourse2 = (TextView) findViewById(R.id.course2);
        tvCourse3 = (TextView) findViewById(R.id.course3);
        tvCourse4 = (TextView) findViewById(R.id.course4);

        tvName.setText(student.getName());
        tvBranch.setText(student.getBranch());
        tvRollNumber.setText(student.getRollNumber());
        tvCourse1.setText("1. "+student.getCourse(0));
        tvCourse2.setText("2. "+student.getCourse(1));
        tvCourse3.setText("3. "+student.getCourse(2));
        tvCourse4.setText("4. "+student.getCourse(3));

        CurrentState = "Create";
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i(TAG, "State of activity "+TAG+" changed from "+CurrentState+" to Start");
        Toast.makeText(this, "State of activity "+TAG+" changed from "+CurrentState+" to Start", Toast.LENGTH_SHORT).show();
        CurrentState = "Start";
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(TAG, "State of activity "+TAG+" changed from "+CurrentState+" to Resume");
        Toast.makeText(this, "State of activity "+TAG+" changed from "+CurrentState+" to Resume", Toast.LENGTH_SHORT).show();
        CurrentState = "Resume";
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i(TAG, "State of activity "+TAG+" changed from "+CurrentState+" to Pause");
        Toast.makeText(this, "State of activity "+TAG+" changed from "+CurrentState+" to Pause", Toast.LENGTH_SHORT).show();
        CurrentState = "Pause";
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.i(TAG, "State of activity "+TAG+" changed from "+CurrentState+" to Restart");
        Toast.makeText(this, "State of activity "+TAG+" changed from "+CurrentState+" to Restart", Toast.LENGTH_SHORT).show();
        CurrentState = "Restart";
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i(TAG, "State of activity "+TAG+" changed from "+CurrentState+" to Stop");
        Toast.makeText(this, "State of activity "+TAG+" changed from "+CurrentState+" to Stop", Toast.LENGTH_SHORT).show();
        CurrentState = "Stop";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "State of activity "+TAG+" changed from "+CurrentState+" to Destroy");
        Toast.makeText(this, "State of activity "+TAG+" changed from "+CurrentState+" to Destroy", Toast.LENGTH_SHORT).show();
        CurrentState = "Destroy";
    }
}
