package com.example.android.happystory.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.happystory.R;
import com.example.android.happystory.data.HappyStory;
import com.example.android.happystory.ui.ReadingActivity;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.happystory.ui.ReadingActivity.STORY_KEY;

public class StoriesAdapterRV extends RecyclerView.Adapter<StoriesAdapterRV.ViewHolder> {
    Context context;
    List <HappyStory> happyStories = new ArrayList<>();

    @NonNull
    @Override
    public StoriesAdapterRV.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.happy_story_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoriesAdapterRV.ViewHolder viewHolder, int pos) {


        viewHolder.title.setText(happyStories.get(pos).getTitle());
        viewHolder.short_des.setText(happyStories.get(pos).getShort_des());

        Picasso.get()
                .load(happyStories.get(pos).getImage())
                .error(R.drawable.no_image)
                .into(viewHolder.image);
    }

    public void setHappyStories(List<HappyStory> happyStories, Context context){
        this.context = context;
        this.happyStories = happyStories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return happyStories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView short_des;
        ImageView image;
        View view_for_anim;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.story_title);
            short_des = (TextView) itemView.findViewById(R.id.story_short_dis);
            image = (ImageView) itemView.findViewById(R.id.story_image);
            view_for_anim = (View) itemView.findViewById(R.id.view);
            itemView.setOnClickListener(this);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View view) {
            HappyStory current_happyStory;
            current_happyStory = happyStories.get(getAdapterPosition());

            Intent intent = new Intent(context, ReadingActivity.class);
            intent.putExtra(STORY_KEY, (Serializable) current_happyStory);


            Pair<View, String> p1 = Pair.create((View)image, "profile");
            Pair<View, String> p2 = Pair.create((View)view_for_anim, "story");
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity)context, p1, p2);
            context.startActivity(intent, options.toBundle());


//            context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
        }
    }

}
