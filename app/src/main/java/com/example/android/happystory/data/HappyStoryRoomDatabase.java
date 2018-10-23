package com.example.android.happystory.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.android.happystory.R;

@Database(entities = {HappyStory.class}, version = 1)
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
                    //     addCallback(happyStoryDatabaseCallback).
                         build();
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback happyStoryDatabaseCallback =
            new RoomDatabase.Callback()  {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final HappyStoryDao happyStoryDao;
        private PopulateDbAsync(HappyStoryRoomDatabase instance) {
            happyStoryDao = instance.happyStoryDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            HappyStory test = new HappyStory(R.drawable.one, "title",
                    "short_des",
                   "long_story",
                   "author",
                    Utils.CATEGORY_ONE);
            happyStoryDao.insert(test);
            return null;
        }
    }


}

