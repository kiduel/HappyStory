package com.example.android.happystory.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "happy_story_table")
public class HappyStory implements Serializable, Parcelable {
    @NonNull
    @PrimaryKey
    private String title;
    private String short_des;
    private String long_story;
    private String author;
    @NonNull
    private int category;
    private int image;
//    @PrimaryKey(autoGenerate = true)
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

    @Ignore
    public HappyStory(int image, String title, String short_des, String long_story, String author, int category) {
        this.title = title;
        this.short_des = short_des;
        this.long_story = long_story;
        this.author = author;
        this.category = category;
        this.image = image;

    }

    protected HappyStory(Parcel in) {
        title = in.readString();
        short_des = in.readString();
        long_story = in.readString();
        author = in.readString();
        category = in.readInt();
        image = in.readInt();
        id = in.readInt();
    }

    public static final Creator<HappyStory> CREATOR = new Creator<HappyStory>() {
        @Override
        public HappyStory createFromParcel(Parcel in) {
            return new HappyStory(in);
        }

        @Override
        public HappyStory[] newArray(int size) {
            return new HappyStory[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(short_des);
        parcel.writeString(long_story);
        parcel.writeString(author);
        parcel.writeInt(category);
        parcel.writeInt(image);
        parcel.writeInt(id);
    }
}
