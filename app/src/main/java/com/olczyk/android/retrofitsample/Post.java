package com.olczyk.android.retrofitsample;

import com.google.gson.annotations.SerializedName;

public class Post {

    private int id;

    private int userId;

    @SerializedName("body")
    private String text;

    private String title;

    public Post(int id, int userId, String text, String title) {
        this.id = id;
        this.userId = userId;
        this.text = text;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
