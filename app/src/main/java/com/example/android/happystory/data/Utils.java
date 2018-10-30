package com.example.android.happystory.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;

public class Utils {
    public static final int CATEGORY_ONE = 1;
    public static final int CATEGORY_TWO = 2;
    public static final int CATEGORY_THREE = 3;
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


    //To check the connection,

    public static Boolean isConnected(Context context){
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

}
