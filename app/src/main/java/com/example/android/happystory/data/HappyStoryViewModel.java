package com.example.android.happystory.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class HappyStoryViewModel extends AndroidViewModel {
    private HappyStoryRepository happyStoryRepository;
    private LiveData<List<HappyStory>> favoriteStories;

    public HappyStoryViewModel(@NonNull Application application) {
        super(application);
        happyStoryRepository = new HappyStoryRepository(application);
        favoriteStories = happyStoryRepository.getFavoriteStories();
    }

    public LiveData<List<HappyStory>> getFavoriteStories() {
        return favoriteStories;
    }

    public void insert (HappyStory happyStory){
        happyStoryRepository.insert(happyStory);
    }

    public void delete (HappyStory happyStory){
        happyStoryRepository.delete(happyStory);
    }

    public void deleteAll (){
        happyStoryRepository.deleteAll();
    }

}
