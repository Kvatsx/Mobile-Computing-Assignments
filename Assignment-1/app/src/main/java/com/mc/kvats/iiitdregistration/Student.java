package com.mc.kvats.iiitdregistration;

import java.util.ArrayList;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Student implements Serializable {
    private String Name;
    private String RollNumber;
    private String Branch;
    private ArrayList<String> Courses;

    public Student(String name, String roll, String branch) {
        this.Name = name;
        this.RollNumber = roll;
        this.Branch = branch;
        this.Courses = new ArrayList<String>();
    }

    public void addCourse(String course) {
        Courses.add(course);
    }

    public String getName() {
        return this.Name;
    }

    public String getRollNumber() {
        return this.RollNumber;
    }

    public String getBranch() {
        return this.Branch;
    }

    public String getCourse(int i) {
        return Courses.get(i);
    }
}
