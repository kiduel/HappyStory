package com.example.android.happystory.data;

public class Post {
    int User_id;
    int id;
    String title;
    String body;

    public Post(int user_id, int id, String title, String body) {
        User_id = user_id;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
