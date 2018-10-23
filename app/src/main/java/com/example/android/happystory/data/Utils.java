package com.example.android.happystory.data;

import android.content.Context;
import android.util.Log;

import com.example.android.happystory.R;
import com.example.android.happystory.network.GetDataService;
import com.example.android.happystory.network.RetrofitClientService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Utils {
    public static final int CATEGORY_ONE = 1;
    public static final int CATEGORY_TWO = 2;
    public static final int CATEGORY_THREE = 3;
    public static List<Story> data_from_json = new ArrayList<>();
    static List<Post> hellow;

    //
    public static ArrayList<HappyStory> all_stories(Context context) {
        HappyStory happyStory_one = new HappyStory(R.drawable.one, context.getResources().getString(R.string.story_title_one),
                context.getResources().getString(R.string.story_title_one_short_des),
                context.getResources().getString(R.string.story_title_one_long_text),
                context.getResources().getString(R.string.story_title_one_author),
                Utils.CATEGORY_ONE);
        HappyStory happyStory_two = new HappyStory(R.drawable.two, context.getResources().getString(R.string.story_title_two),
                context.getResources().getString(R.string.story_title_two_short_des),
                context.getResources().getString(R.string.story_title_two_long_text),
                context.getResources().getString(R.string.story_title_two_author),
                Utils.CATEGORY_THREE);
        HappyStory happyStory_three = new HappyStory(R.drawable.three, context.getResources().getString(R.string.story_title_three),
                context.getResources().getString(R.string.story_title_three_short_des),
                context.getResources().getString(R.string.story_title_three_long_text),
                context.getResources().getString(R.string.story_title_three_author),
                Utils.CATEGORY_TWO);
        HappyStory happyStory_four = new HappyStory(R.drawable.four, context.getResources().getString(R.string.story_title_four),
                context.getResources().getString(R.string.story_title_four_short_des),
                context.getResources().getString(R.string.story_title_four_long_text),
                context.getResources().getString(R.string.story_title_four_author),
                Utils.CATEGORY_THREE);
        HappyStory happyStory_five = new HappyStory(R.drawable.five, context.getResources().getString(R.string.story_title_five),
                context.getResources().getString(R.string.story_title_five_short_des),
                context.getResources().getString(R.string.story_title_five_long_text),
                context.getResources().getString(R.string.story_title_five_author),
                Utils.CATEGORY_ONE);
        HappyStory happyStory_six = new HappyStory(R.drawable.six, context.getResources().getString(R.string.story_title_five),
                context.getResources().getString(R.string.story_title_five_short_des),
                context.getResources().getString(R.string.story_title_five_long_text),
                context.getResources().getString(R.string.story_title_five_author),
                Utils.CATEGORY_ONE);
        HappyStory happyStory_seven = new HappyStory(R.drawable.seven, context.getResources().getString(R.string.story_title_five),
                context.getResources().getString(R.string.story_title_five_short_des),
                context.getResources().getString(R.string.story_title_five_long_text),
                context.getResources().getString(R.string.story_title_five_author),
                Utils.CATEGORY_TWO);
        HappyStory happyStory_eight = new HappyStory(R.drawable.eight, context.getResources().getString(R.string.story_title_five),
                context.getResources().getString(R.string.story_title_five_short_des),
                context.getResources().getString(R.string.story_title_five_long_text),
                context.getResources().getString(R.string.story_title_five_author),
                Utils.CATEGORY_THREE);
        HappyStory happyStory_nine = new HappyStory(R.drawable.nine, context.getResources().getString(R.string.story_title_five),
                context.getResources().getString(R.string.story_title_five_short_des),
                context.getResources().getString(R.string.story_title_five_long_text),
                context.getResources().getString(R.string.story_title_five_author),
                Utils.CATEGORY_TWO);

        ArrayList<HappyStory> mock_stories = new ArrayList<>();
        mock_stories.add(happyStory_one);
        mock_stories.add(happyStory_two);
        mock_stories.add(happyStory_three);
        mock_stories.add(happyStory_four);
        mock_stories.add(happyStory_five);
        mock_stories.add(happyStory_six);
        mock_stories.add(happyStory_seven);
        mock_stories.add(happyStory_eight);
        mock_stories.add(happyStory_nine);

        return mock_stories;
    }


    /*
    This will categorize the stories for us
    @Param 1 is the collection of the stories
    @Param 2 is the category we want
     */
    public static ArrayList<HappyStory> categorize_stories(ArrayList stories, int category) {

        ArrayList<HappyStory> category_one = new ArrayList<>();
        ArrayList<HappyStory> category_two = new ArrayList<>();
        ArrayList<HappyStory> category_three = new ArrayList<>();

        for (int x = 0; x < stories.size(); x++) {
            HappyStory happyStory = (HappyStory) stories.get(x);
            switch (happyStory.getCategory()) {
                case 1:
                    category_one.add(happyStory);
                    break;
                case 2:
                    category_two.add(happyStory);
                    break;
                case 3:
                    category_three.add(happyStory);
                    break;
            }
        }

        switch (category) {
            case 1:
                return category_one;
            case 2:
                return category_two;
            case 3:
                return category_three;
        }
        return null;
    }

    public static ArrayList<Story> UseRetrofit() {

        GetDataService getHappyStory = RetrofitClientService.getRetrofitInstance().create(GetDataService.class);
        Call<List<Story>> call = getHappyStory.getAllHappyStories();
        call.enqueue(new Callback<List<Story>>() {
            @Override
            public void onResponse(Call<List<Story>> call, Response<List<Story>> response) {
                data_from_json = response.body();
                Log.i("TAG", "onCreate: size is " + response.body().size());
            }

            @Override
            public void onFailure(Call<List<Story>> call, Throwable t) {
            }
        });
        return new ArrayList<>(data_from_json);
    }
}
