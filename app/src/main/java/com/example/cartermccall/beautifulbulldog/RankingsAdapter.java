package com.example.cartermccall.beautifulbulldog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.RealmResults;

public class RankingsAdapter extends RecyclerView.Adapter<RankingsAdapter.RankingViewHolder> {

    private Context context;
    private RealmResults<Vote> votes;

    public RankingsAdapter(Context context, RealmResults<Vote> dataSet) {
        this.context = context;
        this.votes = dataSet;
    }

    public class RankingViewHolder extends RecyclerView.ViewHolder {
        public TextView userView;
        public TextView bulldogView;
        public TextView voteView;

        public RankingViewHolder(View v) {
            super(v);
            userView = v.findViewById(R.id.user_view);
            bulldogView = v.findViewById(R.id.bulldog_view);
            voteView = v.findViewById(R.id.vote_view);

        }
    }


        @Override
        public int getItemCount() {
            return votes.size();
        }

        public RankingsAdapter.RankingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.vote_cell, parent, false);
            RankingsAdapter.RankingViewHolder vh = new RankingsAdapter.RankingViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(RankingsAdapter.RankingViewHolder holder, int position) {
            holder.userView.setText(votes.get(position).getOwner().getUsername());
            holder.bulldogView.setText(votes.get(position).getBulldog().getName());
            holder.voteView.setText(Integer.toString(votes.get(position).getRating()));
        }



}

