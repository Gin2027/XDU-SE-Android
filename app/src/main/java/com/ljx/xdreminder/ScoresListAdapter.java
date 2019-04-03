package com.ljx.xdreminder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ScoresListAdapter extends RecyclerView.Adapter<ScoresListAdapter.ScoresViewHodler> {
    List<String> scores_list;

    public ScoresListAdapter(List<String> list)
    {
        scores_list = list;
    }

    public static class ScoresViewHodler extends RecyclerView.ViewHolder
    {
        TextView name;          //课程名称

        public ScoresViewHodler(View view)
        {
            super(view);
            name = view.findViewById(R.id.scores_lesson_name);
        }
    }

    @Override
    public void onBindViewHolder(ScoresViewHodler holder, int position) {
        holder.name.setText(scores_list.get(position));
    }

    @Override
    public ScoresViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scores_list_item,parent,false);
        return new ScoresViewHodler(view);
    }

    @Override
    public int getItemCount() {
        return scores_list.size();
    }
}