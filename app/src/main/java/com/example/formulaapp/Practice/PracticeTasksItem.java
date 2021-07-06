package com.example.formulaapp.Practice;

import java.util.Comparator;

public class PracticeTasksItem implements Comparable<PracticeTasksItem>{

    private String text1;
    private String text2;
    private String id;
    private String status;
    private String difficulty;
    private int category;

public PracticeTasksItem(String text1,String text2,String id,String status,String difficulty,int category){
this.category=category;
    this.text1 =text1;
    this.text2 =text2;
    this.id = id;
    this.status = status;
    this.difficulty=difficulty;
    }



    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public static Comparator<PracticeTasksItem> myName=new Comparator<PracticeTasksItem>() {
        @Override
        public int compare(PracticeTasksItem e1, PracticeTasksItem e2) {
            return e1.getDifficulty().compareTo(e2.getDifficulty());
        }
    };
    public static Comparator<PracticeTasksItem> myName2=new Comparator<PracticeTasksItem>() {
        @Override
        public int compare(PracticeTasksItem e1, PracticeTasksItem e2) {
            return e2.getDifficulty().compareTo(e1.getDifficulty());
        }
    };

    @Override
    public int compareTo(PracticeTasksItem practiceTasksItem) {
        return 0;
    }
}
