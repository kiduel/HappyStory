package com.example.android.happystory.network;

import com.example.android.happystory.data.HappyStory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("clmeheZTSa?indent=2")
    Call<List<HappyStory>> getAllHappyStories();

}
