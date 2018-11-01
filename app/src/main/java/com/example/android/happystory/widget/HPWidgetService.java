package com.example.android.happystory.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.happystory.R;
import com.example.android.happystory.data.HappyStory;
import com.example.android.happystory.ui.MainActivity;

import java.util.ArrayList;


public class HPWidgetService extends RemoteViewsService {
    public static final String HappyStoryWidget_KEY = "happy_story";

    //We need this class to prepare and manipulate the data

    /*
    Returns a RemoteViewsFactory object,
     the object we create below
     */
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new HPItemFactory(getApplicationContext(), intent);
    }

    /*
    This serves us the adapter,
    Everything the widget needs to access (such as data) is in a different process
    We use RemoteViewsFactory as an Adapter,
     */
    class HPItemFactory implements RemoteViewsFactory {
        private Context context;
        private int appWidgetId;
        private String [] data = {"There is a farmer", "Who had a dog", "And Bingo was his name, Oh"};
        private ArrayList<HappyStory> hPWidgetArrayList = new ArrayList<>();

        public HPItemFactory(Context context, Intent intent) {
            this.context = context;
            this.appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

        }


        @Override
        public void onCreate() {
            // This will be triggered when we instantiate
//            hPWidgetArrayList.add(new HappyStory(null, "title_one", "short_des", "long_des", "me", 3));
//            hPWidgetArrayList.add(new HappyStory(null, "title_two", "short_des", "long_des", "me", 2));
//            hPWidgetArrayList.add(new HappyStory(null, "title_three", "short_des", "long_des", "me", 1));
//            hPWidgetArrayList.add(new HappyStory(null, "title_four", "short_des", "long_des", "me", 1));

            hPWidgetArrayList = MainActivity.fav_stories;

        }

        @Override
        public void onDataSetChanged() {
           // hPWidgetArrayList = MainActivity.fav_stories;
            Log.i("TAG", "onDataSetChanged: " + hPWidgetArrayList.size());

        }

        @Override
        public void onDestroy() {
            // Close the connection
        }

        @Override
        public int getCount() {
            return hPWidgetArrayList.size();
        }

        @Override
        public RemoteViews getViewAt(int pos) {
            HappyStory happyStory = hPWidgetArrayList.get(pos);
            //Each data will be assigned to a remoteViews

            Bundle bundle = new Bundle();
            bundle.putSerializable(HappyStoryWidget_KEY, happyStory);
            Intent fillIntent = new Intent();
            fillIntent.putExtras(bundle);


            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_item);
            remoteViews.setOnClickFillInIntent(R.id.widget_item_text, fillIntent);
            remoteViews.setTextViewText(R.id.widget_item_text, happyStory.getTitle());

            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            // if we want to change the default loading text
            // the one that is going to be displayed
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int pos) {
            return pos;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
