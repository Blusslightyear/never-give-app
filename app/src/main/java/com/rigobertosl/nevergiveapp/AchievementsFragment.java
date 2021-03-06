package com.rigobertosl.nevergiveapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import static java.lang.Integer.valueOf;

public class AchievementsFragment extends Fragment {

    private DataBaseContract db;
    private int page;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        page = valueOf(getArguments().getInt("page_position"));
        db = new DataBaseContract(getActivity());

        View rootView = inflater.inflate(R.layout.fragment_achievements, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.list_achivements);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        String achievementType = "";
        if (page == 1){
            achievementType = "foods";
        }else if (page == 2){
            achievementType = "training";
        }

        db.open();
        ArrayList<Achievement> achievements = db.getAllAchievementsByType(achievementType);
        db.close();
        RecyclerView.Adapter adapterTrain = new CustomAchievementAdapter(achievements);
        recyclerView.setAdapter(adapterTrain);

        return rootView;
    }
}
