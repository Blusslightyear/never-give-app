package com.rigobertosl.nevergiveapp;

public class ExerciseCard {

    private String name;
    private int series;
    private String repeticiones;
    private int descanso;

    public ExerciseCard(){}

    public ExerciseCard(String name, int series, String repeticiones, int descanso){
        this.name = name;
        this.series = series;
        this.repeticiones = repeticiones;
        this.descanso = descanso;
    }

    public ExerciseCard(String repeticiones){
        this.repeticiones = repeticiones;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public String getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(String repeticiones) {
        this.repeticiones = repeticiones;
    }

    public int getDescanso() {
        return descanso;
    }

    public void setDescanso(int descanso) {
        this.descanso = descanso;
    }
}
