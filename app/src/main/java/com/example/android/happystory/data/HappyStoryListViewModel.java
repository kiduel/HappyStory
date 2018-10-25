package com.example.android.happystory.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class HappyStoryListViewModel extends ViewModel {
    private MutableLiveData<List<HappyStory>> mutableLiveDataHappyStory;

    public LiveData<List<HappyStory>> getHappyStories(){
        if (mutableLiveDataHappyStory == null){
            mutableLiveDataHappyStory = new MutableLiveData<List<HappyStory>>();
            loadHappyStories();
        }
        return mutableLiveDataHappyStory;
    }

    private void loadHappyStories() {
        HappyStoryListRepository happyStoryListRepository = new HappyStoryListRepository();
        mutableLiveDataHappyStory = happyStoryListRepository.getHappyStories();
    }
}
