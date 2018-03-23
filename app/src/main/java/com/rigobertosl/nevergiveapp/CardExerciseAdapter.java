package com.rigobertosl.nevergiveapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CardExerciseAdapter extends RecyclerView.Adapter<CardExerciseAdapter.MyViewHolder> {

    private Context context;
    private List<ExerciseCard> exerciseList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, repeticiones;
        public ImageView imageExercise;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            repeticiones = (TextView) view.findViewById(R.id.card_repetitions);
            imageExercise = (ImageView) view.findViewById(R.id.card_image);
        }
    }

    public CardExerciseAdapter(Context context, List<ExerciseCard> exerciseList) {
        this.context = context;
        this.exerciseList = exerciseList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        ExerciseCard exercise = exerciseList.get(position);
        holder.title.setText(exercise.getName());
        holder.repeticiones.setText(exercise.getRepeticiones() + " repeticiones");
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
