package com.example.android.happystory.ui;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.android.happystory.R;
import com.example.android.happystory.adapters.StoriesAdapterRV;
import com.example.android.happystory.data.HappyStory;
import com.example.android.happystory.data.HappyStoryViewModel;
import com.example.android.happystory.data.Story;
import com.example.android.happystory.data.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rv_stories)
    RecyclerView rv_stories;

    @BindView(R.id.linear_container)
    LinearLayout linearLayout;


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.nav_draw_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_drawer)
    NavigationView navigationView;

    StoriesAdapterRV storiesAdapterRV;
    Context context = this;

    ArrayList<HappyStory> fav_stories = new ArrayList<>();
    ArrayList<HappyStory> stories_displayed = new ArrayList<>();
    public static final String KEY_FOR_SAVED_PREF = "key_for_saved_pref";
    public static final String HOME = "home";
    public static final String CAT_ONE = "one";
    public static final String CAT_TWO = "two";
    public static final String CAT_THREE = "three";


    public static HappyStoryViewModel happyStoryViewModel;
    String menu;
    boolean is_fav_selected;


    public LiveData<List<HappyStory>> happyStoryLiveData;
    LifecycleOwner lifecycleOwner;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SetUpToolBar();
        SetUpNavBar();
        storiesAdapterRV = new StoriesAdapterRV();
        lifecycleOwner = this;

        if (savedInstanceState == null) {
            stories_displayed = Utils.all_stories(context);
            DisplayStories(stories_displayed);
        } else {
            DisplayStories(savedInstanceState.<HappyStory>getParcelableArrayList(KEY_FOR_SAVED_PREF));
            stories_displayed = savedInstanceState.<HappyStory>getParcelableArrayList(KEY_FOR_SAVED_PREF);
        }

        ArrayList<Story> x = Utils.UseRetrofit();
        Log.i("TAG", "onCreate: sizeee " + x.size());
        stories_displayed = Utils.categorize_stories(Utils.all_stories(context), 1);


        happyStoryViewModel = ViewModelProviders.of(this).get(HappyStoryViewModel.class);
        happyStoryLiveData = happyStoryViewModel.getFavoriteStories();

        happyStoryLiveData.observe(lifecycleOwner,
                new Observer<List<HappyStory>>() {
                        @Override
                        public void onChanged(@Nullable List<HappyStory> stories) {
                            size = stories.size();
                            fav_stories = (ArrayList<HappyStory>) stories;

                            Log.i("TAG size", "onChanged: " + size +
                            "boolean " + is_fav_selected);

                            if (is_fav_selected ){
                                setTitle(getResources().getString(R.string.favorite));
                                DisplayStories(stories);
                            }
                        }
        });
    }



    private void SetUpToolBar(){
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void SetUpNavBar() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                ArrayList<HappyStory> hp = new ArrayList<>();
                switch (menuItem.getItemId()) {
                    case R.id.home_nav_bar:
                        menu = HOME;
                        is_fav_selected = false;
                        DisplayStories(Utils.all_stories(context));
                        toolbar.setTitle(R.string.app_name);
                        hp = Utils.all_stories(context);

                        break;
                    case R.id.favorite_nav_bar:
                        toolbar.setTitle("favorite");
                        is_fav_selected = true;

                        if (size > 0){ rv_stories.setVisibility(View.VISIBLE);
                        DisplayStories(fav_stories);
                        hp = fav_stories;
                        }
                        else {rv_stories.setVisibility(View.INVISIBLE);
                        linearLayout.setBackgroundColor(getResources().getColor(R.color.green));}
                        break;

                    case R.id.category_one_nav_bar:
                        menu = CAT_ONE;
                        is_fav_selected = false;
                        DisplayStories(Utils.categorize_stories(Utils.all_stories(context), 1));
                        hp = Utils.categorize_stories(Utils.all_stories(context), 1);

                        Toast.makeText(MainActivity.this, "One", Toast.LENGTH_SHORT).show();
                        toolbar.setTitle(R.string.category_one);
                        break;
                    case R.id.category_two_nav_bar:
                        menu = CAT_TWO;
                        is_fav_selected = false;
                        DisplayStories(Utils.categorize_stories(Utils.all_stories(context), 2));
                        hp = Utils.categorize_stories(Utils.all_stories(context), 2);

                        Toast.makeText(MainActivity.this, "Two", Toast.LENGTH_SHORT).show();
                        toolbar.setTitle(R.string.category_two);
                        break;
                    case R.id.category_three_nav_bar:
                        menu = CAT_THREE;
                        is_fav_selected = false;
                        DisplayStories(Utils.categorize_stories(Utils.all_stories(context), 3));
                        hp = Utils.categorize_stories(Utils.all_stories(context), 3);

                        Toast.makeText(MainActivity.this, "Three", Toast.LENGTH_SHORT).show();
                        toolbar.setTitle(R.string.category_three);
                        break;
                    case R.id.nav_share:
                        is_fav_selected = false;
                        happyStoryViewModel.deleteAll();
                        break;
                }
                stories_displayed = hp;
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void DisplayStories(List<HappyStory> stories) {
        storiesAdapterRV.setHappyStories(stories, context);
        rv_stories.setLayoutManager(new LinearLayoutManager(this));
        rv_stories.setVisibility(View.VISIBLE);
        rv_stories.setHasFixedSize(true);
        rv_stories.setAdapter(storiesAdapterRV);
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
        outState.putParcelableArrayList(KEY_FOR_SAVED_PREF, stories_displayed);
        Log.i("TAG", "onSaveInstanceState: " + stories_displayed.size());
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
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

}
