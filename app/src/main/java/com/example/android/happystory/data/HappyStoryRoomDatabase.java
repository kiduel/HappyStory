package com.example.android.happystory.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {HappyStory.class}, version = 1, exportSchema = false)
public abstract class HappyStoryRoomDatabase extends RoomDatabase {
    public abstract HappyStoryDao happyStoryDao();

    private static volatile HappyStoryRoomDatabase INSTANCE;

    /*
    creates HappyStoryRoomDatabase if it does not exist,
    if an instance HappyStoryRoomDatabase exists, it does not create another object.
    instead, it returns the one we are using earlier.
     */
    static HappyStoryRoomDatabase getDatabase(final Context context) {
        synchronized (HappyStoryRoomDatabase.class) {
            if ( INSTANCE == null ) {
                 INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        HappyStoryRoomDatabase.class,
                        "happy_story_database").
                         fallbackToDestructiveMigration().
                         build();
            }
        }
        return INSTANCE;
    }

}

