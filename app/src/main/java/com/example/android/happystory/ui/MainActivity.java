package com.example.android.happystory.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.happystory.R;
import com.example.android.happystory.adapters.StoriesAdapterRV;
import com.example.android.happystory.data.HappyStory;
import com.example.android.happystory.data.HappyStoryListViewModel;
import com.example.android.happystory.data.HappyStoryViewModel;
import com.example.android.happystory.data.Utils;
import com.example.android.happystory.network.DownloadQuoteIntent;
import com.example.android.happystory.network.SampleResultReceiver;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.happystory.network.DownloadQuoteIntent.ACTION_FETCH_IMG;
import static com.example.android.happystory.network.DownloadQuoteIntent.EXTRA_RECEIVER;
import static com.example.android.happystory.network.DownloadQuoteIntent.EXTRA_URL;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_FOR_SAVED_PREF = "key_for_saved_pref";
    public static final String KEY_FOR_TITLE = "key_for_title";
    public static final String KEY_FOR_FAV_BOOL = "key_for_boolean";
    public static final String KEY_FOR_QUOTES_BOOL = "key_for_boolean_quotes";
    private static final String KEY_FOR_QUOTE_IMG = "key_for_quote_image";
    public static HappyStoryViewModel happyStoryViewModel;
    public LiveData<List<HappyStory>> happyStoryLiveData;
    @BindView(R.id.rv_stories)
    RecyclerView rv_stories;
    @BindView(R.id.linear_container)
    LinearLayout linearLayout;
    @BindView(R.id.no_connection)
    TextView no_connection;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_draw_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_drawer)
    NavigationView navigationView;
    @BindView(R.id.a_quote)
    ImageView quote_img_view;
    @BindView(R.id.fav_is_empty)
    TextView empty_fav;
    StoriesAdapterRV storiesAdapterRV;
    Context context = this;
    Bitmap quote_image;


    public static ArrayList<HappyStory> fav_stories = new ArrayList<>();
    ArrayList<HappyStory> stories_displayed = new ArrayList<>();
    SampleResultReceiver resultReceiver;
    boolean is_fav_selected, is_quotes_selected;
    int size;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        storiesAdapterRV = new StoriesAdapterRV();
        resultReceiver = new SampleResultReceiver(this, new Handler(Looper.getMainLooper()));
        SetUpToolBar();

        /*
        If there is no internet connection, just show the favorites.
         */
        if ( !Utils.isConnected(context) )
        {
            SetUpNavBar(null);
            hideRVShowNoConnection();

            //If fav is selected, we display the favorite section, even if the app is offline
            if ( savedInstanceState != null ) {
                is_fav_selected = savedInstanceState.getBoolean(KEY_FOR_FAV_BOOL);
                setTitle(savedInstanceState.getString(KEY_FOR_TITLE));

                if ( !is_fav_selected ) {
                    hideRVShowNoConnection();
                } else {
                    rv_stories.setVisibility(View.VISIBLE);
                    no_connection.setVisibility(View.GONE);
                    empty_fav.setVisibility(View.GONE);
                    DisplayStories(savedInstanceState.<HappyStory>getParcelableArrayList(KEY_FOR_SAVED_PREF));
                    stories_displayed = savedInstanceState.<HappyStory>getParcelableArrayList(KEY_FOR_SAVED_PREF);
                }
            }
        }
        /*
        If there is connection
        */
        else {
            if (savedInstanceState != null ) {
                is_quotes_selected = savedInstanceState.getBoolean(KEY_FOR_QUOTES_BOOL);
                if ( is_quotes_selected ) {
                    setTitle(R.string.quotes);
                    displayQuote();

                    Bitmap bitmap = savedInstanceState.getParcelable(KEY_FOR_QUOTE_IMG);
                    quote_img_view.setImageBitmap(bitmap);
                }
            }

            HappyStoryListViewModel happyStoryListViewModel = ViewModelProviders.of(this).get(HappyStoryListViewModel.class);
            happyStoryListViewModel.getHappyStories().observe(this, new Observer<List<HappyStory>>() {
                @Override
                public void onChanged(@Nullable List<HappyStory> happyStories) {
                    {
                        stories_displayed = new ArrayList<>(happyStories);
                        SetUpNavBar(stories_displayed);
                        /*
                        If quote is not selected, show the list
                         */
                        if ( !is_quotes_selected ) {
                            Log.i("TAG", "onChanged: from listLive is running");

                            if ( savedInstanceState == null ) {
                                Log.i("TAG", "onChanged: from listLive is running");
                                DisplayStories(stories_displayed);
                            } else {
                                setTitle(savedInstanceState.getString(KEY_FOR_TITLE));
                                ArrayList<HappyStory> to_be_dis = savedInstanceState.<HappyStory>getParcelableArrayList(KEY_FOR_SAVED_PREF);
                                Log.i("TAG", "onCreate: to_be_dis " + to_be_dis);
                                DisplayStories(to_be_dis);
                                stories_displayed = to_be_dis;
                            }
                        }
                    }
                }
            });

            happyStoryViewModel = ViewModelProviders.of(this).get(HappyStoryViewModel.class);
            happyStoryLiveData = happyStoryViewModel.getFavoriteStories();

            happyStoryLiveData.observe(this,
                    new Observer<List<HappyStory>>() {
                        @Override
                        public void onChanged(@Nullable List<HappyStory> stories) {
                            size = stories.size();
                            fav_stories = (ArrayList<HappyStory>) stories;

                            if ( is_fav_selected ) {
                                setTitle(getResources().getString(R.string.favorite));
                                DisplayStories(stories);
                                if ( stories.size() == 0 ) {
                                    favEmpty();
                                }
                            }
                        }
                    });
        }
    }

    private void SetUpToolBar() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void SetUpNavBar(final ArrayList<HappyStory> happyStories) {
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if ( happyStories == null ) {
                    // If the there is no internet
                    switch (menuItem.getItemId()) {
                        case R.id.favorite_nav_bar:
                            setTitle(R.string.favorite);
                            if ( size > 0 ) {
                                stories_displayed = fav_stories;
                                DisplayStories(fav_stories);
                            } else {
                                favEmpty();
                            }
                            is_fav_selected = true;
                            break;
                        default:
                            setTitle(R.string.app_name);
                            hideRVShowNoConnection();
                    }
                } else
                    switch (menuItem.getItemId()) {
                        case R.id.home_nav_bar:
                            setTitle(R.string.app_name);
                            is_fav_selected = false;
                            is_quotes_selected = false;
                            stories_displayed = happyStories;
                            DisplayStories(stories_displayed);
                            break;
                        case R.id.quotes_nav_bar:
                            setTitle(R.string.quotes);
                            displayQuote();
                            is_quotes_selected = true;
                            break;
                        case R.id.favorite_nav_bar:
                            is_fav_selected = true;
                            is_quotes_selected = false;
                            setTitle(R.string.favorite);
                            quote_img_view.setVisibility(View.GONE);
                            if ( size > 0 ) {
                                rv_stories.setVisibility(View.VISIBLE);
                                empty_fav.setVisibility(View.GONE);
                                stories_displayed = fav_stories;
                                DisplayStories(stories_displayed);
                            } else {
                                favEmpty();
                            }
                            break;

                        case R.id.category_one_nav_bar:
                            setTitle(R.string.category_one);
                            DisplayFromNav(happyStories, Utils.CATEGORY_SPIRITUAL);
                            break;
                        case R.id.category_two_nav_bar:
                            setTitle(R.string.category_two);
                            DisplayFromNav(happyStories, Utils.CATEGORY_RELATIONSHIP);
                            break;
                        case R.id.category_three_nav_bar:
                            setTitle(R.string.category_three);
                            DisplayFromNav(happyStories, Utils.CATEGORY_HEALTH);
                            break;
                        case R.id.nav_share:
                            is_fav_selected = false;
                            happyStoryViewModel.deleteAll();
                            Toast.makeText(context, getString(R.string.share_place_holder), Toast.LENGTH_SHORT).show();
                            break;
                    }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void displayQuote() {
        is_fav_selected = false;
        is_quotes_selected = true;
        rv_stories.setVisibility(View.GONE);
        empty_fav.setVisibility(View.GONE);
        no_connection.setVisibility(View.GONE);

        quote_img_view.setVisibility(View.VISIBLE);

        Intent intent = new Intent(this, DownloadQuoteIntent.class);
        intent.setAction(ACTION_FETCH_IMG);
        intent.putExtra(EXTRA_RECEIVER, resultReceiver);
        intent.putExtra(EXTRA_URL, getResources().getString(R.string.quote_img));
        startService(intent);
    }

    private void DisplayFromNav(ArrayList<HappyStory> story_from_nav, int category) {
        is_fav_selected = false;
        stories_displayed = Utils.categorize_stories(story_from_nav, category);
        DisplayStories(stories_displayed);
    }

    private void DisplayStories(List<HappyStory> stories) {
        storiesAdapterRV.setHappyStories(stories, context);
        int resId = R.anim.layout_animation_fall_down;
        int fade_in = R.anim.fade_in_animation;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(context, resId);
        rv_stories.setLayoutAnimation(animation);
        rv_stories.setLayoutManager(new LinearLayoutManager(context));
        rv_stories.setVisibility(View.VISIBLE);
        rv_stories.setHasFixedSize(true);
        rv_stories.setAdapter(storiesAdapterRV);
    }

    private void hideRVShowNoConnection() {
        is_fav_selected = false;
        rv_stories.setVisibility(View.GONE);
        empty_fav.setVisibility(View.GONE);
        no_connection.setVisibility(View.VISIBLE);
    }

    private void favEmpty() {
        rv_stories.setVisibility(View.GONE);
        no_connection.setVisibility(View.GONE);
        empty_fav.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if ( drawerLayout.isDrawerOpen(GravityCompat.START) ) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(KEY_FOR_QUOTES_BOOL, is_quotes_selected);
        outState.putBoolean(KEY_FOR_FAV_BOOL, is_fav_selected);
        outState.putString(KEY_FOR_TITLE, toolbar.getTitle().toString());
        outState.putParcelableArrayList(KEY_FOR_SAVED_PREF, stories_displayed);
        outState.putParcelable(KEY_FOR_QUOTE_IMG, quote_image);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete_all:
                happyStoryViewModel.deleteAll();
                Toast.makeText(context, "all stories deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;

    }


    public void onImageDownloaded(Bitmap bmp) {
        quote_image = bmp;
        quote_img_view.setImageBitmap(quote_image);
    }
}


