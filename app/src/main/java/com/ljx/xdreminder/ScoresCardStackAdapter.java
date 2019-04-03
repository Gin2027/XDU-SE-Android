package com.ljx.xdreminder;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.StackAdapter;

import java.util.List;


public class ScoresCardStackAdapter extends StackAdapter<Integer> {
    //Context mContext;
    List<List<String>> lessonList;
    List<String> titleList;
    public ScoresCardStackAdapter(Context context)
    {
        super(context);
        //mContext = context;
    }


    public void updateData(List data,List<String> titleList,List<List<String>> lessonList) {
        this.lessonList = lessonList;
        this.titleList = titleList;
        updateData(data);
    }

    @Override
    protected CardStackView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scores_card_item,parent,false);
        CardViewHolder holder = new CardViewHolder(view);
        return holder;
    }

    @Override
    public void bindView(Integer data, int position, CardStackView.ViewHolder holder) {
        if(holder instanceof CardViewHolder)
        {
            CardViewHolder cardHolder = (CardViewHolder)holder;
            cardHolder.onBind(data,position,lessonList,titleList);
        }
    }


    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public Integer getItem(int position) {
        return super.getItem(position);
    }

    public static class CardViewHolder extends CardStackView.ViewHolder
    {
        View root;
        FrameLayout cardTitle;
        RecyclerView scoreList;
        TextView titleText;
        public CardViewHolder(View view)
        {
            super(view);
            root = view;
            cardTitle = view.findViewById(R.id.card_title);
            titleText = view.findViewById(R.id.card_title_text);
            scoreList = view.findViewById(R.id.scores_list);
        }

        public void onBind(Integer backgroundColorId,int position,List<List<String>> dataList,List<String> titles)
        {
            cardTitle.getBackground().setColorFilter(ContextCompat.getColor(getContext(),backgroundColorId), PorterDuff.Mode.SRC_IN);
            titleText.setText(titles.get(position));
            ScoresListAdapter adapter = new ScoresListAdapter(dataList.get(position));
            scoreList.setLayoutManager(new LinearLayoutManager(getContext()));
            scoreList.setAdapter(adapter);
        }

        @Override
        public void onItemExpand(boolean b) {
            scoreList.setVisibility(b ? View.VISIBLE : View.GONE);
        }
    }
}
