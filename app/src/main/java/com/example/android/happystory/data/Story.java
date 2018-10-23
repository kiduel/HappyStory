package com.example.android.happystory.data;

public class Story {
    int category;
    String long_story;
    String author;
    String image;
    String title;
    String short_des;

    public Story(int category, String long_story, String author, String image, String title, String short_des) {
        this.category = category;
        this.long_story = long_story;
        this.author = author;
        this.image = image;
        this.title = title;
        this.short_des = short_des;
    }

    public int getCategory() {
        return category;
    }

    public String getLong_story() {
        return long_story;
    }

    public String getAuthor() {
        return author;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getShort_des() {
        return short_des;
    }
}
