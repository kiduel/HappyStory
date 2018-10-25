package com.example.android.happystory.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class HappyStoryRepository {
    private HappyStoryDao happyStoryDao;
    private LiveData<List<HappyStory>> favoriteStories;


    HappyStoryRepository(Application application) {
        HappyStoryRoomDatabase db = HappyStoryRoomDatabase.getDatabase(application);
        this.happyStoryDao = db.happyStoryDao();
        this.favoriteStories = happyStoryDao.getAllStories();
    }

    LiveData<List<HappyStory>> getFavoriteStories() {
        return favoriteStories;
    }

    public void insert(HappyStory happyStory){
        new insertAsyncTask(happyStoryDao).execute(happyStory);
    }

    public void delete(HappyStory happyStory){
        new deleteAsyncTask(happyStoryDao).execute(happyStory);
    }


    public void deleteAll(){
        new deleteAll(happyStoryDao).execute();
    }

    private class insertAsyncTask extends AsyncTask<HappyStory, Void, Void> {
        private HappyStoryDao happyStoryDao;
        public insertAsyncTask(HappyStoryDao dao) {
            happyStoryDao = dao;
        }

        @Override
        protected Void doInBackground(final HappyStory...params) {
            happyStoryDao.insert(params[0]);
            return null;
        }
    }


    private class deleteAll extends AsyncTask <Void, Void, Void> {
        private HappyStoryDao happyStoryDao;
        public deleteAll(HappyStoryDao dao) {
            happyStoryDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            happyStoryDao.deleteAll();
            return null;
        }
    }

    private class deleteAsyncTask extends AsyncTask <HappyStory, Void, Void> {
        private HappyStoryDao happyStoryDao;
        public deleteAsyncTask(HappyStoryDao dao) {
            happyStoryDao = dao;
        }

        @Override
        protected Void doInBackground(final HappyStory... params) {
            happyStoryDao.delete(params[0]);
            return null;
        }
    }

}
