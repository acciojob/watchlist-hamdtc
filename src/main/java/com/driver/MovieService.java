package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    void insertMovie(Movie movie){
        String name = movie.getName();
        movieRepository.addMovieInDatabase(name, movie);
    }
    void insertDirector(Director director){
        String name = director.getName();
        movieRepository.addDirectorInDatabase(name, director);
    }
    void insertMovieDirector(String mov_name, String dir_name){
        movieRepository.mapMovieDirector(mov_name, dir_name);
    }
    public Movie getMovieByName(String name){
        return movieRepository.getMovieByNameDB(name);
    }
    public Director getDirectorByName(String name){
        return movieRepository.getDirectorByNameDB(name);
    }
    public List<Movie> getMovieListByDirectorName(String name){
        return movieRepository.getMoviesByDirectorName(name);
    }
    public List<Movie> getListOfMovies(){
        return movieRepository.getMoviesListFromDB();
    }
    public void deleteDirectorByName(String name){
        movieRepository.deleteDirectorByNameDB(name);
    }
    public void DeleteAllDirectors() {
        movieRepository.deleteAllDirectors();
    }
}