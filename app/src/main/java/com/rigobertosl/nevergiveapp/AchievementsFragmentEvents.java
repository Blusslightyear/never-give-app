package com.rigobertosl.nevergiveapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AchievementsFragmentEvents extends Fragment {
    public static AchievementsFragmentEvents newInstance (int sectionNumber){
        final String ARG_SECTION_NUMBER = "section_number";
        AchievementsFragmentEvents fragment = new AchievementsFragmentEvents();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_achievements_prueba, container, false);
    }


}

