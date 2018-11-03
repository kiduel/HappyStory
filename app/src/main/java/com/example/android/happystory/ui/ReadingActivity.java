package com.example.android.happystory.ui;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.android.happystory.R;
import com.example.android.happystory.adapters.StoriesReadingAdapterRV;
import com.example.android.happystory.data.HappyStory;
import com.example.android.happystory.widget.HPWidgetProvider;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadingActivity extends AppCompatActivity {
    public static final String STORY_KEY = "story_key";

    HappyStory happyStory;
    StoriesReadingAdapterRV storiesReadingAdapterRV;
    @BindView(R.id.rv_happy_stories_read)
    RecyclerView rv_story;
    @BindView(R.id.pic_image)
    ImageView img_story;
    @BindView(R.id.fab_star_secondary)
    FloatingActionButton fab_star_secondary;

    ArrayList<HappyStory> fav_stories = new ArrayList<>();
    Boolean isStoryFav, bool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        fav_stories = MainActivity.fav_stories;
        happyStory = (HappyStory) getIntent().getExtras().getSerializable(STORY_KEY);
        ButterKnife.bind(this);


        if (happyStory == null) {
            String title = getIntent().getStringExtra(HPWidgetProvider.EXTRA_TITLE);
            String short_des = getIntent().getStringExtra(HPWidgetProvider.EXTRA_SHORT_DES);
            String long_story = getIntent().getStringExtra(HPWidgetProvider.EXTRA_LONG_TEXT);
            String author = getIntent().getStringExtra(HPWidgetProvider.EXTRA_AUTHOR);
            String image = getIntent().getStringExtra(HPWidgetProvider.EXTRA_IMG);
            int category = getIntent().getIntExtra(HPWidgetProvider.EXTRA_CATEGORY, -1);

            happyStory = new HappyStory(image, title, short_des, long_story, author, category);
        }
        setUpUI();


        storiesReadingAdapterRV = new StoriesReadingAdapterRV(this, breakToParagraph(happyStory.getLong_story()));
        rv_story.setAdapter(storiesReadingAdapterRV);
        rv_story.setLayoutManager(new LinearLayoutManager(this));

        if ( fav_stories.size() > 0 ) {
            isStoryFav = (checkIfStoryIsAlreadyInFav(happyStory, fav_stories));
            Log.i("TAG", "reading_activity: " + isStoryFav);
        } else {
            isStoryFav = false;
        }

        if ( isStoryFav ) {
            fab_star_secondary.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite));
            bool = true;
        } else {
            fab_star_secondary.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_border));
            bool = false;
        }

        fab_star_secondary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( bool ) {
                    fab_star_secondary.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_border));
                    deleteFromFavorite();
                    Snackbar.make(view, "Story removed to favorites", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    fab_star_secondary.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite));
                    insertToFavorite();
                    Snackbar.make(view, "Story added to favorites", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                bool = !bool; // reverse
            }
        });


    }

    private void insertToFavorite() {
        MainActivity.happyStoryViewModel.insert(happyStory);
         }

    private void deleteFromFavorite() {
        MainActivity.happyStoryViewModel.delete(happyStory);

    }


    private ArrayList<String> breakToParagraph(String long_story) {
        String[] stringArray = long_story.split("endofP");
        return new ArrayList<>(Arrays.asList(stringArray));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpUI() {
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_tbr);
        collapsingToolbar.setTitle(happyStory.getTitle());

        Picasso.get()
                .load(happyStory.getImage())
                .error(R.drawable.no_image)
                .into(img_story);
    }

    private boolean checkIfStoryIsAlreadyInFav(HappyStory happy_story, ArrayList<HappyStory> fav_stories) {
        String title_of_passed_story = happy_story.getTitle();
        for (int x = 0; x < fav_stories.size(); x++) {
            HappyStory hp = fav_stories.get(x);
            if ( hp.getTitle().equals(title_of_passed_story) ) {
                return true;
            }
        }
        return false;
    }
}