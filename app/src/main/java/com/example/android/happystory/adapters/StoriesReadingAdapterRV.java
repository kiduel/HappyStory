package com.example.android.happystory.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.happystory.R;

import java.util.ArrayList;

public class StoriesReadingAdapterRV extends RecyclerView.Adapter<StoriesReadingAdapterRV.ViewHolder> {
    Context context;
    ArrayList<String> paragraphs;

    public StoriesReadingAdapterRV(Context context, ArrayList<String> paragraphs) {
        this.context = context;
        this.paragraphs = paragraphs;
    }


    @NonNull
    @Override
    public StoriesReadingAdapterRV.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.paragraph_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoriesReadingAdapterRV.ViewHolder viewHolder, int pos) {
        viewHolder.textView.setText((paragraphs.get(pos)));
    }


    @Override
    public int getItemCount() {
        return paragraphs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.paragraph);
        }
    }
}
