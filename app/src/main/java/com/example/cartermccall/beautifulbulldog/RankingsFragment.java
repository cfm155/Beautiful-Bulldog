package com.example.cartermccall.beautifulbulldog;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class RankingsFragment extends Fragment {

    private RecyclerView rankingList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter rankingAdapter;


    public RankingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rankings, container, false);

        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Vote> votes = realm.where(Vote.class).findAll();
        rankingList = (RecyclerView) view.findViewById(R.id.ranking_list);


        layoutManager = new LinearLayoutManager(getContext());
        rankingList.setLayoutManager(layoutManager);

        rankingAdapter = new RankingsAdapter(getContext(),votes);
        rankingList.setAdapter(rankingAdapter);


        return view;
    }

}
