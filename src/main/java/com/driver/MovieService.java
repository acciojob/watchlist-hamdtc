package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;


//1   Add a movie: POST /movies/add-movie
//    Pass the Movie object as request body
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - addMovie

    void addMovie( Movie movie) {
        String movieName = movie.getName();
        if(!movieRepository.moviesSet.containsKey(movieName)) {
            movieRepository.addMovie(movieName,movie);
        }
    }

//2    Add a director: POST /movies/add-director
//    Pass the Director object as request body
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - addDirector

    void addDirector(Director director) {
        String directorName = director.getName();
        if(!movieRepository.directorsSet.containsKey(directorName)) {
            movieRepository.addDirector(directorName,director);
        }
    }

//3    Pair an existing movie and director: PUT /movies/add-movie-director-pair
//    Pass movie name and director name as request parameters
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - addMovieDirectorPair

    void addMovieDirectorPair( String movieName, String directorName) {

        if(movieRepository.directorsSet.containsKey(directorName) && movieRepository.moviesSet.containsKey(movieName)) {

            Director director = movieRepository.directorsSet.get(directorName);
            Movie movie = movieRepository.moviesSet.get(movieName);

            if(movieRepository.pair.get(director) == null){
                List<Movie> listOfMovies = new ArrayList<>();
                listOfMovies.add(movie);
                director.setNumberOfMovies(director.getNumberOfMovies() +1);
                movieRepository.addMovieDirectorPair(director, listOfMovies);
            }
            else{
                List<Movie> listOfMovies = movieRepository.pair.get(director);
                for(Movie movies : listOfMovies){
                    if(movies.getName().equalsIgnoreCase(movieName)){
                        return;
                    }
                }
                director.setNumberOfMovies(director.getNumberOfMovies() +1);
                listOfMovies.add(movie);
                movieRepository.addMovieDirectorPair(director, listOfMovies);
            }

        }

    }


//4    Get Movie by movie name: GET /movies/get-movie-by-name/{name}
//    Pass movie name as path parameter
//    Return Movie object wrapped in a ResponseEntity object
//    Controller Name - getMovieByName


    Movie getMovieByName( String movieName) {
        return movieRepository.getMovieByName(movieName);
    }

//5    Get Director by director name: GET /movies/get-director-by-name/{name}
//    Pass director name as path parameter
//    Return Director object wrapped in a ResponseEntity object
//    Controller Name - getDirectorByName


    Director getDirectorByName(String directorName) {
        return movieRepository.getDirectorByName(directorName);
    }


//    6    Get List of movies name for a given director name: GET /movies/get-movies-by-director-name/{director}
//    Pass director name as path parameter
//    Return List of movies name(List()) wrapped in a ResponseEntity object
//    Controller Name - getMoviesByDirectorName


    List<String> getMoviesByDirectorName(String directorName) {

        Director director = movieRepository.directorsSet.get(directorName);
        List<String> namesOfMovies = new ArrayList<>();

        if(!movieRepository.pair.containsKey(director)) {
            return namesOfMovies;
        }
        List<Movie> listOfMovies = movieRepository.getMoviesByDirectorName(director);

        for(Movie movie : listOfMovies) {
            namesOfMovies.add(movie.getName());
        }
        return namesOfMovies;
    }

//    7    Get List of all movies added: GET /movies/get-all-movies
//    No params or body required
//    Return List of movies name(List()) wrapped in a ResponseEntity object
//    Controller Name - findAllMovies


    List<String> findAllMovies() {
        List<String> listOfMovies = new ArrayList<>();
        for(String movieName : movieRepository.moviesSet.keySet()) {
            listOfMovies.add(movieName);
        }
        return listOfMovies;
    }

//    8    Delete a director and its movies from the records: DELETE /movies/delete-director-by-name
//    Pass director’s name as request parameter
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - deleteDirectorByName


    void deleteDirectorByName(String directorName) {

        Director director = movieRepository.directorsSet.get(directorName);

        movieRepository.directorsSet.remove(directorName);

        List <Movie> listOfMovies = movieRepository.pair.get(director);
        for( Movie movie : listOfMovies) {
            String movieName = movie.getName();
            movieRepository.moviesSet.remove(movieName);
        }

        movieRepository.pair.remove(director);

    }



//    9    Delete all directors and all movies by them from the records: DELETE /movies/delete-director-by-name
//    No params or body required
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - deleteAllDirectors
//    (Note that there can be some movies on your watchlist that aren’t mapped to any of the director. Make sure you do not remove them.)

    void deleteAllDirectors() {

        for(List <Movie>listOfMovies : movieRepository.pair.values()) {
            for(Movie movie : listOfMovies) {
                String movieName = movie.getName();
                movieRepository.moviesSet.remove(movieName);
            }
        }

        movieRepository.deleteAllDirectors();
    }

}