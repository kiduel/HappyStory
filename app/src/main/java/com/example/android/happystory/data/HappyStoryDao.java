package com.example.android.happystory.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
interface  HappyStoryDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(HappyStory happyStory);

    @Query("DELETE FROM happy_story_table")
    void deleteAll();

    @Query("SELECT * from happy_story_table ORDER BY title ASC")
    LiveData<List<HappyStory>> getAllStories();

    @Delete
    void delete(HappyStory happyStory);
}
