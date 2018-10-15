package com.example.android.happystory.data;

import android.content.Context;

import com.example.android.happystory.R;

import java.util.ArrayList;

public class Utils {
    public static final int CATEGORY_ONE = 1;
    public static final int CATEGORY_TWO = 2;
    public static final int CATEGORY_THREE = 3;


    public static ArrayList<HappyStory> createMockStories(Context context) {
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
        HappyStory happyStory_six = new HappyStory(R.drawable.five, context.getResources().getString(R.string.story_title_five),
                context.getResources().getString(R.string.story_title_five_short_des),
                context.getResources().getString(R.string.story_title_five_long_text),
                context.getResources().getString(R.string.story_title_five_author),
                Utils.CATEGORY_ONE);
        HappyStory happyStory_seven = new HappyStory(R.drawable.five, context.getResources().getString(R.string.story_title_five),
                context.getResources().getString(R.string.story_title_five_short_des),
                context.getResources().getString(R.string.story_title_five_long_text),
                context.getResources().getString(R.string.story_title_five_author),
                Utils.CATEGORY_ONE);
        HappyStory happyStory_eight = new HappyStory(R.drawable.five, context.getResources().getString(R.string.story_title_five),
                context.getResources().getString(R.string.story_title_five_short_des),
                context.getResources().getString(R.string.story_title_five_long_text),
                context.getResources().getString(R.string.story_title_five_author),
                Utils.CATEGORY_ONE);
        HappyStory happyStory_nine = new HappyStory(R.drawable.five, context.getResources().getString(R.string.story_title_five),
                context.getResources().getString(R.string.story_title_five_short_des),
                context.getResources().getString(R.string.story_title_five_long_text),
                context.getResources().getString(R.string.story_title_five_author),
                Utils.CATEGORY_ONE);
        HappyStory happyStory_ten = new HappyStory(R.drawable.five, context.getResources().getString(R.string.story_title_five),
                context.getResources().getString(R.string.story_title_five_short_des),
                context.getResources().getString(R.string.story_title_five_long_text),
                context.getResources().getString(R.string.story_title_five_author),
                Utils.CATEGORY_ONE);

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
        mock_stories.add(happyStory_ten);


        return mock_stories;
    }
    public static ArrayList<HappyStory> createMockStoriesTwo(Context context) {
        HappyStory happyStory_one = new HappyStory(R.drawable.one, context.getResources().getString(R.string.story_title_one),
                context.getResources().getString(R.string.story_title_one_short_des),
                context.getResources().getString(R.string.story_title_one_long_text),
                context.getResources().getString(R.string.story_title_one_author),
                Utils.CATEGORY_ONE);


        ArrayList<HappyStory> mock_stories = new ArrayList<>();
        mock_stories.add(happyStory_one);
        return mock_stories;
    }
}