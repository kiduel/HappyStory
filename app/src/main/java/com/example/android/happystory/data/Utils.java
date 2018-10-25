package com.example.android.happystory.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.android.happystory.R;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static final int CATEGORY_ONE = 1;
    public static final int CATEGORY_TWO = 2;
    public static final int CATEGORY_THREE = 3;
    public static List<Story> data_from_json = new ArrayList<>();
    public static List<HappyStory> data_from_json_hp = new ArrayList<>();

    static List<Post> hellow;

    //
    public static ArrayList<HappyStory> all_stories(Context context) {
        HappyStory happyStory_one = new HappyStory("http://www.myiconfinder.com/uploads/iconsets/256-256-9ea3f65bc3bb904b14755c83370d90bb.png", context.getResources().getString(R.string.story_title_one),
                context.getResources().getString(R.string.story_title_one_short_des),
                context.getResources().getString(R.string.story_title_one_long_text),
                context.getResources().getString(R.string.story_title_one_author),
                Utils.CATEGORY_ONE);
        HappyStory happyStory_two = new HappyStory("https://images.freeimages.com/images/premium/small-comps/3703/37034356-cartoon-number-two.jpg", context.getResources().getString(R.string.story_title_two),
                context.getResources().getString(R.string.story_title_two_short_des),
                context.getResources().getString(R.string.story_title_two_long_text),
                context.getResources().getString(R.string.story_title_two_author),
                Utils.CATEGORY_THREE);
        HappyStory happyStory_three = new HappyStory("https://cdn2.iconfinder.com/data/icons/video-games-10/32/number_three_top_earn-128.png", context.getResources().getString(R.string.story_title_three),
                context.getResources().getString(R.string.story_title_three_short_des),
                context.getResources().getString(R.string.story_title_three_long_text),
                context.getResources().getString(R.string.story_title_three_author),
                Utils.CATEGORY_TWO);
        HappyStory happyStory_four = new HappyStory("http://iconbug.com/data/83/256/d226a6bda108dcd82122ea450d3e8cd9.png", context.getResources().getString(R.string.story_title_four),
                context.getResources().getString(R.string.story_title_four_short_des),
                context.getResources().getString(R.string.story_title_four_long_text),
                context.getResources().getString(R.string.story_title_four_author),
                Utils.CATEGORY_THREE);
        HappyStory happyStory_five = new HappyStory("https://www.stonybrook.edu/commcms/japan/links/images/five.png", context.getResources().getString(R.string.story_title_five),
                context.getResources().getString(R.string.story_title_five_short_des),
                context.getResources().getString(R.string.story_title_five_long_text),
                context.getResources().getString(R.string.story_title_five_author),
                Utils.CATEGORY_ONE);
        HappyStory happyStory_six = new HappyStory("http://demo.microcad.ca/web/talisman/wp-content/themes/talisman/images/process-icon/6.png", context.getResources().getString(R.string.story_title_five),
                context.getResources().getString(R.string.story_title_five_short_des),
                context.getResources().getString(R.string.story_title_five_long_text),
                context.getResources().getString(R.string.story_title_five_author),
                Utils.CATEGORY_ONE);
        HappyStory happyStory_seven = new HappyStory("https://is4-ssl.mzstatic.com/image/thumb/Purple49/v4/52/88/49/52884933-d912-df61-22cc-a675881e647e/source/256x256bb.jpg", context.getResources().getString(R.string.story_title_five),
                context.getResources().getString(R.string.story_title_five_short_des),
                context.getResources().getString(R.string.story_title_five_long_text),
                context.getResources().getString(R.string.story_title_five_author),
                Utils.CATEGORY_TWO);
        HappyStory happyStory_eight = new HappyStory("https://ubisafe.org/images/eyghen-clipart-black-and-white-6.png", context.getResources().getString(R.string.story_title_five),
                context.getResources().getString(R.string.story_title_five_short_des),
                context.getResources().getString(R.string.story_title_five_long_text),
                context.getResources().getString(R.string.story_title_five_author),
                Utils.CATEGORY_THREE);
        HappyStory happyStory_nine = new HappyStory("http://getdrawings.com/images/nine-drawing-38.png", context.getResources().getString(R.string.story_title_five),
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


    //To check the connection,

    public static Boolean isConnected(Context context){
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

}
