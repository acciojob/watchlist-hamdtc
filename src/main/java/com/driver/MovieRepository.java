package com.driver;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    HashMap<String, Movie> MovieMap = new HashMap<>();
    HashMap<String, Director> DirectorMap = new HashMap<>();
    HashMap<Movie, Director> MovieDirectorMap = new HashMap<>();

    void mapMovieDirector(String mov_name, String dir_name){
        Movie movie = MovieMap.get(mov_name);
        Director director = DirectorMap.get(dir_name);
        MovieDirectorMap.put(movie,director);
    }
    void addMovieInDatabase(String name, Movie movie){
        MovieMap.put(name,movie);
    }
    void addDirectorInDatabase(String name, Director director){
        DirectorMap.put(name,director );
    }
    public Movie getMovieByNameDB(String name){
        return MovieMap.get(name);
    }
    public Director getDirectorByNameDB(String name){
        return DirectorMap.get(name);
    }
    public List<Movie> getMoviesByDirectorName(String name){
        List<Movie> movieList = new ArrayList<>();
        if(MovieMap.containsKey(name)){
            movieList.add(MovieMap.get(name));
        }
        return movieList;
    }
    public List<Movie> getMoviesListFromDB(){
        List<Movie> movieList = new ArrayList<>();
        for (Map.Entry<String, Movie> movieEntry: MovieMap.entrySet()){
            movieList.add(movieEntry.getValue());
        }
        return movieList;
    }
    public void deleteAllDirectors() {
        for(String d : DirectorMap.keySet()){
            if(DirectorMap.containsKey(d)){
                DirectorMap.remove(d);
            }
        }
        DirectorMap.clear();
    }
    public void deleteDirectorByNameDB(String name){
        DirectorMap.remove(name);
        for(Movie key: MovieDirectorMap.keySet()){
            if (MovieDirectorMap.get(key).getName().equals(name)){
                MovieMap.remove(key.getName());
                MovieDirectorMap.remove(key);
            }
        }

    }
}
