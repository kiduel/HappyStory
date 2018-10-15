package com.example.android.happystory.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.happystory.R;
import com.example.android.happystory.adapters.StoriesAdapterRV;
import com.example.android.happystory.data.HappyStory;
import com.example.android.happystory.data.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rv_stories)
    RecyclerView rv_stories;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.nav_draw_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_drawer)
    NavigationView navigationView;

    ArrayList<HappyStory> happyStoryList;
    StoriesAdapterRV storiesAdapterRV;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SetUpToolBar();
        SetUpNavBar();
        DisplayStories(Utils.createMockStories(this));

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
                switch (menuItem.getItemId()) {
                    case R.id.home_nav_bar:
                        DisplayStories(Utils.createMockStories(context));
                        toolbar.setTitle(R.string.app_name);
                        break;
                    case R.id.favorite_nav_bar:
                        DisplayStories(Utils.createMockStoriesTwo(context));
                        toolbar.setTitle(R.string.favorite);
                        break;
                    case R.id.category_one_nav_bar:
                        Toast.makeText(MainActivity.this, "One", Toast.LENGTH_SHORT).show();
                        toolbar.setTitle(R.string.category_one);
                        break;
                    case R.id.category_two_nav_bar:
                        Toast.makeText(MainActivity.this, "Two", Toast.LENGTH_SHORT).show();
                        toolbar.setTitle(R.string.category_two);
                        break;
                    case R.id.category_three_nav_bar:
                        Toast.makeText(MainActivity.this, "Three", Toast.LENGTH_SHORT).show();
                        toolbar.setTitle(R.string.category_three);
                        break;
                    case R.id.nav_share:
                        Toast.makeText(MainActivity.this, "Three", Toast.LENGTH_SHORT).show();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void DisplayStories(ArrayList<HappyStory> stories) {
        storiesAdapterRV = new StoriesAdapterRV(this, stories);
        rv_stories.setLayoutManager(new LinearLayoutManager(this));
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

}
