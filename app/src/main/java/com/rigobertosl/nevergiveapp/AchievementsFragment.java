package com.rigobertosl.nevergiveapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static java.lang.Integer.valueOf;

public class AchievementsFragment extends Fragment {

    private DataBaseContract db;
    private int weekDay;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        weekDay = valueOf(getArguments().getInt("page_position"));
        db = new DataBaseContract(getActivity());

        View rootView = inflater.inflate(R.layout.fragment_achievements, container, false);

        

        return rootView;
    }
}
