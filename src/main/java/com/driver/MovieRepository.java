package com.driver;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@org.springframework.stereotype.Repository
public class MovieRepository {

    HashMap<String, Movie> moviesSet = new HashMap<>();
    HashMap<String, Director> directorsSet = new HashMap<>();
    HashMap<Director, List<Movie>> pair = new HashMap<>();



    void addMovie( String movieName, Movie movie) {
        moviesSet.put(movieName,movie);
    }


    void addDirector(String directorName, Director director) {
        directorsSet.put(directorName, director);
    }

    void addMovieDirectorPair( Director director, List<Movie> listOfMovies) {

        pair.put(director, listOfMovies);

    }



    Movie getMovieByName( String movieName) {
        return moviesSet.get(movieName);

    }


    Director getDirectorByName(String directorName) {
        return directorsSet.get(directorName);
    }


    List<Movie> getMoviesByDirectorName(Director director) {
        return pair.get(director);
    }



//    List<String> findAllMovies() {
//        List<String> moviesName = new ArrayList<>();
//        for(String mo)
//    }


//    void deleteDirectorByName(String directorName) {
//
//    }



    void deleteAllDirectors() {
        this.directorsSet = new HashMap<>();
        this.pair = new HashMap<>();
    }


}