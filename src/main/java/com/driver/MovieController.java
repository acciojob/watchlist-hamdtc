package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

//1   Add a movie: POST /movies/add-movie
//    Pass the Movie object as request body
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - addMovie

    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody() Movie movie) {

        movieService.addMovie(movie);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

//2    Add a director: POST /movies/add-director
//    Pass the Director object as request body
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - addDirector

    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody() Director director) {

        movieService.addDirector(director);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

//3    Pair an existing movie and director: PUT /movies/add-movie-director-pair
//    Pass movie name and director name as request parameters
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - addMovieDirectorPair

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movie") String movieName,
                                               @RequestParam("director") String directorName) {

        movieService.addMovieDirectorPair(movieName,directorName);

        return new ResponseEntity<>("success", HttpStatus.OK);

    }


//4    Get Movie by movie name: GET /movies/get-movie-by-name/{name}
//    Pass movie name as path parameter
//    Return Movie object wrapped in a ResponseEntity object
//    Controller Name - getMovieByName

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String movieName) {

        Movie movie = movieService.getMovieByName(movieName);
        return new ResponseEntity<>(movie ,HttpStatus.OK);
    }

//5    Get Director by director name: GET /movies/get-director-by-name/{name}
//    Pass director name as path parameter
//    Return Director object wrapped in a ResponseEntity object
//    Controller Name - getDirectorByName

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String directorName) {

        Director director = movieService.getDirectorByName(directorName);
        return new ResponseEntity<>(director ,HttpStatus.OK);
    }


//    6    Get List of movies name for a given director name: GET /movies/get-movies-by-director-name/{director}
//    Pass director name as path parameter
//    Return List of movies name(List()) wrapped in a ResponseEntity object
//    Controller Name - getMoviesByDirectorName

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String directorName) {

        List<String> listOfMovies = movieService.getMoviesByDirectorName(directorName);
        return new ResponseEntity<>(listOfMovies ,HttpStatus.OK);
    }

//    7    Get List of all movies added: GET /movies/get-all-movies
//    No params or body required
//    Return List of movies name(List()) wrapped in a ResponseEntity object
//    Controller Name - findAllMovies

    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies() {

        List<String> listOfMovies = movieService.findAllMovies();
        return new ResponseEntity<>(listOfMovies ,HttpStatus.OK);
    }

//    8    Delete a director and its movies from the records: DELETE /movies/delete-director-by-name
//    Pass director’s name as request parameter
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - deleteDirectorByName

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("director") String directorName) {

        movieService.deleteDirectorByName(directorName);
        return new ResponseEntity<>("success" ,HttpStatus.OK);
    }


//    9    Delete all directors and all movies by them from the records: DELETE /movies/delete-director-by-name
//    No params or body required
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - deleteAllDirectors
//    (Note that there can be some movies on your watchlist that aren’t mapped to any of the director. Make sure you do not remove them.)

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors() {

        movieService.deleteAllDirectors();
        return new ResponseEntity<>("success" ,HttpStatus.OK);
    }

}