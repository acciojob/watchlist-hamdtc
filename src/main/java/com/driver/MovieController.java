package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    // HashMap<String,Movie>movies = new HashMap<>();

    @Autowired
    MovieService movieService;


    @Autowired
    MovieRepository movieRepository;



    //running successfully
    @PostMapping("/add-movie")
    public ResponseEntity<String> insertMovie(@RequestBody(required = true)Movie movie){
        // movies.put(movie.getName(), movie);
        movieService.insertMovie(movie);
        return new ResponseEntity<>("sucess", HttpStatus.CREATED);
    }

    //running
    @PostMapping("/add-director")
    public ResponseEntity<String> insertDirector(@RequestBody()Director director){

        movieService.insertDirector(director);

        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }


    //running
    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies(){
        List<Movie>listOfMovies = movieService.getListOfMovies();

        return new ResponseEntity<>(listOfMovies,HttpStatus.OK);

    }

    // running but output is not there
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name")String name){

        //try {
        Movie movie = movieService.getMovieByName(name);
        return new ResponseEntity<>(movie,HttpStatus.OK);
        // }catch (Exception e) {
        //return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        //}

    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathParam("director")String directorname){
        //try {
        Director d = movieService.getDirectorByName(directorname);
        return new ResponseEntity<>(d,HttpStatus.OK);
        //}catch (Exception e){
        //    return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);
        //}

    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@PathParam("directorName")String name){
        movieService.deleteDirectorByName(name);
        return new ResponseEntity<>("Sucess",HttpStatus.OK );
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity  deleteAllDirectors(){
        movieService.DeleteAllDirectors();
        return new ResponseEntity("Sucess",HttpStatus.OK);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movieName")String movieName,@RequestParam("directorName")String directorName){

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("directorName")String directorName){
        List<Movie>moviesByDirector = movieService.getMovieListByDirectorName(directorName);
        return new ResponseEntity(moviesByDirector,HttpStatus.OK);
    }
}