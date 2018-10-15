package com.example.android.happystory.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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

import java.util.List;

import static com.example.android.happystory.ui.ReadingActivity.STORY_KEY;

public class StoriesAdapterRV extends RecyclerView.Adapter<StoriesAdapterRV.ViewHolder> {
    Context context;
    List <HappyStory> happyStories;

    public StoriesAdapterRV(Context context, List<HappyStory> happyStories) {
        this.context = context;
        this.happyStories = happyStories;
    }

    @NonNull
    @Override
    public StoriesAdapterRV.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.happy_story_list, viewGroup, false);
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

    @Override
    public int getItemCount() {
        return happyStories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView short_des;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.story_title);
            short_des = (TextView) itemView.findViewById(R.id.story_short_dis);
            image = (ImageView) itemView.findViewById(R.id.story_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            HappyStory current_happyStory;
            current_happyStory = happyStories.get(getAdapterPosition());

            Intent intent = new Intent(context, ReadingActivity.class);
            intent.putExtra(STORY_KEY, current_happyStory);
            context.startActivity(intent);
        }
    }

}
