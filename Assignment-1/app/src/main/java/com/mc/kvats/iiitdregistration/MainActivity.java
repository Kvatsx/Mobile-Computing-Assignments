package com.mc.kvats.iiitdregistration;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    public static String CurrentState;

    EditText etName, etRollNumber, etBranch, etCourse1, etCourse2, etCourse3, etCourse4;
    Button btSubmit, btClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.name);
        etRollNumber = (EditText) findViewById(R.id.rollNumber);
        etBranch = (EditText) findViewById(R.id.branch);
        etCourse1 = (EditText) findViewById(R.id.course1);
        etCourse2 = (EditText) findViewById(R.id.course2);
        etCourse3 = (EditText) findViewById(R.id.course3);
        etCourse4 = (EditText) findViewById(R.id.course4);

        btSubmit = (Button) findViewById(R.id.submit);
        btClear = (Button) findViewById(R.id.clear);

        CurrentState = "Create";
    }

    public void DisplayStudentDetails(View v) {
        if ( etName.getText().toString().trim().isEmpty() ) {
            Toast.makeText(this, "Enter your name!", Toast.LENGTH_SHORT).show();
            return;
        }
        else if ( etRollNumber.getText().toString().trim().isEmpty() ) {
            Toast.makeText(this, "Please enter your roll number!", Toast.LENGTH_SHORT).show();
            return;
        }
        else if ( etBranch.getText().toString().trim().isEmpty() ) {
            Toast.makeText(this, "Please enter your branch!", Toast.LENGTH_SHORT).show();
            return;
        }
        else if ( etCourse1.getText().toString().trim().isEmpty() && etCourse2.getText().toString().trim().isEmpty() && etCourse3.getText().toString().trim().isEmpty() && etCourse4.getText().toString().trim().isEmpty() ) {
            Toast.makeText(this, "Please enter your course details!", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            Student student = new Student(etName.getText().toString().trim(), etRollNumber.getText().toString().trim(), etBranch.getText().toString().trim());
            student.addCourse(etCourse1.getText().toString().trim());
            student.addCourse(etCourse2.getText().toString().trim());
            student.addCourse(etCourse3.getText().toString().trim());
            student.addCourse(etCourse4.getText().toString().trim());
//            Log.d(TAG, "Student Object Created.");
            Intent i = new Intent(MainActivity.this, StudentActivity.class);
            i.putExtra("studentObject", student);
//            Log.d(TAG, "Student Object transfer ready for next activity");
            startActivity(i);
        }
    }

    public void ClearAllFields(View v) {
        etName.getText().clear();
        etRollNumber.getText().clear();
        etBranch.getText().clear();
        etCourse1.getText().clear();
        etCourse2.getText().clear();
        etCourse3.getText().clear();
        etCourse4.getText().clear();
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
