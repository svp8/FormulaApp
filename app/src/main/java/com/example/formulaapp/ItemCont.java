package com.example.formulaapp;

public class ItemCont {

    private int imageResource;
    private String Text1;
    private String Text2;
    private String favStatus;
    private  int themeId;

    public ItemCont(int imageResource, String text1, String text2, String favStatus,int themeId) {
        this.imageResource = imageResource;
        this.Text1 =text1;
        this.Text2 = text2;
        this.favStatus = favStatus;
        this.themeId=themeId;
    }
    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }



    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }

    public String getText1() {
        return Text1;
    }

    public void setText1(String text1) {
        Text1 = text1;
    }

    public String getText2() {
        return Text2;
    }

    public void setText2(String text2) {
        Text2 = text2;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }
}

