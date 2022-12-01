package com.driver;

public class Movie {
    private String name;
    private int durationInMinutes;
    private double imdbRating;

    public Movie() {
    }

    public Movie(String name, int durationInMinute, double imdbRating) {
        this.name = name;
        this.durationInMinutes = durationInMinute;
        this.imdbRating = imdbRating;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinute(int durationInMinute) {
        this.durationInMinutes = durationInMinute;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }
}
