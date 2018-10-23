package com.example.android.happystory.ui;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.happystory.R;
import com.example.android.happystory.adapters.StoriesReadingAdapterRV;
import com.example.android.happystory.data.HappyStory;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadingActivity extends AppCompatActivity {
    public static final String STORY_KEY = "story_key";
    public static final String FAVORITE_KEY = "favorite_key";
    HappyStory happyStory;
    StoriesReadingAdapterRV storiesReadingAdapterRV;
    ArrayList<Integer> num = new ArrayList<>();
    @BindView(R.id.rv_happy_stories_read)
    RecyclerView rv_story;
    @BindView(R.id.fab_star)
    FloatingActionButton fab_star;
    @BindView(R.id.fab_star_secondary)
    FloatingActionButton fab_star_secondary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        happyStory = (HappyStory) getIntent().getExtras().getSerializable(STORY_KEY);
        ButterKnife.bind(this);
        setUpUI();

        storiesReadingAdapterRV = new StoriesReadingAdapterRV(this, breakToParagraph(happyStory.getLong_story()));
        rv_story.setAdapter(storiesReadingAdapterRV);
        rv_story.setLayoutManager(new LinearLayoutManager(this));


        fab_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertToFavorite();
            }
        });
        fab_star_secondary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFromFavorite();
            }
        });
    }

    private void insertToFavorite() {
            fab_star.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite));
            MainActivity.happyStoryViewModel.insert(happyStory);
            Toast.makeText(this, "Story added to fav", Toast.LENGTH_SHORT).show();
    }

    private void deleteFromFavorite() {
        fab_star_secondary.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_border));
        MainActivity.happyStoryViewModel.delete(happyStory);
        Toast.makeText(this, "Story removed from fav", Toast.LENGTH_SHORT).show();

    }


    private ArrayList<String> breakToParagraph(String long_story) {
        String [] stringArray = long_story.split("endofP");
        return new ArrayList<>(Arrays.asList(stringArray));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpUI(){
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_tbr);
        collapsingToolbar.setTitle(happyStory.getTitle());
    }
}
