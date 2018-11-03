package com.example.android.happystory.data;

import android.arch.lifecycle.MutableLiveData;

import com.example.android.happystory.network.GetDataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HappyStoryListRepository {
    private static Retrofit retrofit;
    public static final String BASE_URL = "https://s3.amazonaws.com/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public MutableLiveData<List<HappyStory>> getHappyStories(){
        final MutableLiveData<List<HappyStory>> happyStoriesMutableLiveData = new MutableLiveData<>();
        GetDataService getDataService = getRetrofitInstance().create(GetDataService.class);

        getDataService.getAllHappyStories().enqueue(new Callback<List<HappyStory>>() {
            @Override
            public void onResponse(Call<List<HappyStory>> call, Response<List<HappyStory>> response) {
                happyStoriesMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<HappyStory>> call, Throwable t) {

            }
        });

        return happyStoriesMutableLiveData;
    }

}
