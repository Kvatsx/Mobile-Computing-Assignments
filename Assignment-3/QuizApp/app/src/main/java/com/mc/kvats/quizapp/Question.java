package com.mc.kvats.quizapp;

public class Question {

    private String Question;
    private String Description;

    public Question(String question, String description) {
        Question = question;
        Description = description;
    }

    public String getQuestion() {
        return Question;
    }

    public String getDescription() {
        return Description;
    }
}
