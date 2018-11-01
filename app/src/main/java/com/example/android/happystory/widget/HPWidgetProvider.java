package com.example.android.happystory.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.android.happystory.R;
import com.example.android.happystory.data.HappyStory;
import com.example.android.happystory.ui.MainActivity;
import com.example.android.happystory.ui.ReadingActivity;

import static com.example.android.happystory.widget.HPWidgetService.HappyStoryWidget_KEY;

/*
AppWidgetProvider is an extension of BroadCast Receiver.
 */
public class HPWidgetProvider extends AppWidgetProvider {
    //We create ACTION_TOAST to distinguish between the different actions available.
    //We are going to pass it to the onReceive method in the BroadCast receiver.
    public static final String ACTION_DISPLAY_FAV_STORIES = "display_fav_stories";
    public static final String EXTRA_TITLE = "title_of_story";
    public static final String EXTRA_SHORT_DES = "short_des_of_story";
    public static final String EXTRA_AUTHOR = "author_of_story";
    public static final String EXTRA_CATEGORY = "cat_of_story";
    public static final String EXTRA_IMG = "img_of_story";
    public static final String EXTRA_ID = "id_of_story";
    public static final String EXTRA_LONG_TEXT = "long_text_story";



    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        /*
        We loop to update all the instance
         */
        for (int appWidgetId : appWidgetIds ){
            Intent open_main_intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, open_main_intent, 0);

            //We start the service we created,
            //We pass the appWidgetId of each widget to the service
            Intent serviceIntent = new Intent(context, HPWidgetService.class);
            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_display);

            Intent clickIntent = new Intent(context, HPWidgetProvider.class);
            clickIntent.setAction(ACTION_DISPLAY_FAV_STORIES);
            PendingIntent clickPending = PendingIntent.getBroadcast(context, 0, clickIntent, 0);

            views.setOnClickPendingIntent(R.id.widget_button, pendingIntent);
            views.setRemoteAdapter(R.id.list_widget, serviceIntent);
            views.setEmptyView(R.id.list_widget, R.id.empty_widget);
            views.setPendingIntentTemplate(R.id.list_widget, clickPending);


            ComponentName component=new ComponentName(context,HPWidgetProvider.class);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.list_widget);
            appWidgetManager.updateAppWidget(component, views);

//            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.list_widget); // R.id.lessons - it's your listview id
//            appWidgetManager.updateAppWidget(appWidgetId, views);
//

            /*
            To make lists or stacks clickable, we will not assign pendingIntent to each cards or list,
            What we do instead is, we create a PendingIntentTemplate, it will contain the PendingIntent,
            and fillIntent. We will set the PendingIntentTemplate to the stackView,
            we then customize the action using the fillIntent.
             */


        }
    }

    @Override
    public  void onReceive(Context context, Intent intent) {
        if (ACTION_DISPLAY_FAV_STORIES.equals(intent.getAction())){
            Bundle bundle = intent.getExtras();
            HappyStory happyStory = (HappyStory) bundle.getSerializable(HappyStoryWidget_KEY);
            Log.i("TAG", "onReceive: " + happyStory.getTitle());
            Intent intent_two = new Intent(context, ReadingActivity.class);
            intent_two.putExtra(EXTRA_TITLE, happyStory.getTitle());
            intent_two.putExtra(EXTRA_SHORT_DES, happyStory.getShort_des());
            intent_two.putExtra(EXTRA_AUTHOR, happyStory.getAuthor());
            intent_two.putExtra(EXTRA_IMG, happyStory.getImage());
            intent_two.putExtra(EXTRA_ID, happyStory.getId());
            intent_two.putExtra(EXTRA_CATEGORY, happyStory.getCategory());
            intent_two.putExtra(EXTRA_LONG_TEXT, happyStory.getLong_story());


            context.startActivity(intent_two);
        }
        super.onReceive(context, intent);
    }
}
