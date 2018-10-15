package com.example.android.happystory.data;

import java.io.Serializable;

public class HappyStory implements Serializable {
    private String title;
    private String short_des;
    private String long_story;
    private String author;
    private int category;
    private int image;
    private int id;

    public HappyStory(int id, int image, String title, String short_des, String long_story, String author, int category) {
        this.image = image;
        this.title = title;
        this.short_des = short_des;
        this.long_story = long_story;
        this.author = author;
        this.category = category;
        this.id = id;
    }

    public HappyStory(int image, String title, String short_des, String long_story, String author, int category) {
        this.title = title;
        this.short_des = short_des;
        this.long_story = long_story;
        this.author = author;
        this.category = category;
        this.image = image;

    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getShort_des() {
        return short_des;
    }

    public String getLong_story() {
        return long_story;
    }

    public String getAuthor() {
        return author;
    }

    public int getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }
}
