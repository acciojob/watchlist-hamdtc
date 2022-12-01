package com.driver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {



    //this hashmap map the movie name and Movie object
    HashMap<String,Movie> Movie_map = new HashMap<>();
    //this @PathVariable int x map the director name and Director object

    HashMap<String,Director> Director_map = new HashMap<>();


    HashMap<String,List<String>> direct_movies_map = new HashMap<>();

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){

        Movie_map.put(movie.getName(),movie);
        return new ResponseEntity<>("successfully", HttpStatus.CREATED);
    }
    //http://localhost:8030/movies/add-movie

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director)
    {

        Director_map.put(director.getName(),director);
        return new ResponseEntity<>("successfully", HttpStatus.CREATED);
    }
    //http://localhost:8030/movies/add-director


    //Pair an existing movie and director
    @PutMapping("/add-movie-director-pair/{movie_name}/{director_name}")
    public ResponseEntity<String> addMovieDirectorPair(@PathVariable String movie_name,@PathVariable String director_name)
    {

        if(Movie_map.containsKey(movie_name) && Director_map.containsKey(director_name))
        {
            List<String> m = new ArrayList<>();
            if(direct_movies_map.containsKey(director_name))
            {
                m = direct_movies_map.get(director_name);
            }
            m.add(movie_name);
            direct_movies_map.put(director_name,m);
        }
        return new ResponseEntity<>("successfully", HttpStatus.CREATED);

    }
    //http://localhost:8030/movies/add-movie-director-pair/{director_name}/{movie_name}



    //return movie object by movie name
    @GetMapping("/get-movie-by-name/{name}")
    public  ResponseEntity<Movie>  getMovieByName(@PathVariable String name)
    {

        if(Movie_map.containsKey(name))
        {
            return  new ResponseEntity<>(Movie_map.get(name), HttpStatus.CREATED);

        }
        return  new ResponseEntity<>(Movie_map.get(name), HttpStatus.CREATED);
    }
    //http://localhost:8030/movies/get-movie-by-name/{name}

    //Get Director by director name
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name)
    {

        if(Director_map.containsKey(name))
        {
            Director director = Director_map.get(name);
            return  new ResponseEntity<>(director, HttpStatus.CREATED);
        }
        return  new ResponseEntity<>( Director_map.get(name), HttpStatus.CREATED);

    }
    //http://localhost:8030/movies/get-director-by-name/{name}

    //Get List of movies name for a given director name
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director)
    {
        List<String> m = new ArrayList<>();
        if(direct_movies_map.containsKey(director))
        {
            m = direct_movies_map.get(director);
        }
        return  new ResponseEntity<>(m, HttpStatus.CREATED);
    }
    //http.//localhost:8030/movies/get-movies-by-director-name/{director}

    //Get List of all movies added
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies()
    {
        List<String> m = new ArrayList<>();

        for(String name : Movie_map.keySet())
        {
            m.add(Movie_map.get(name).getName());
        }
        return  new ResponseEntity<>(m, HttpStatus.CREATED);
    }
    //http://localhost:8030/movies/get-all-movies

    //Delete a director and its movies from the records
    @DeleteMapping("/delete-director-by-name/{name}")
    public ResponseEntity<String> deleteDirectorByName(@PathVariable String name)
    {
        List<String > m = new ArrayList<>();
        if(direct_movies_map.containsKey(name))
        {
            m = direct_movies_map.get(name);
            for(String movie : m)
            {
                if(Movie_map.containsKey(movie))
                {
                    Movie_map.remove(movie);
                }
            }
            direct_movies_map.remove(name);
        }
        if(Director_map.containsKey(name))
        {
            Director_map.remove(name);
        }
        return new ResponseEntity<>("successfully", HttpStatus.CREATED);
    }
    //http://localhost:8030/delete-director-by-name/{name}

    //Delete all directors and all movies by them from the records
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors()
    {
        List<String> m = new ArrayList<>();

        for(String key : direct_movies_map.keySet())
        {
            for(String movie : direct_movies_map.get(key))
            {
                m.add(movie);
            }
        }
        for(String movie : m)
        {
            if(Movie_map.containsKey(movie))
            {
                Movie_map.remove(movie);
            }
        }

        Director_map.clear();
        direct_movies_map.clear();
        return new ResponseEntity<>("successfully", HttpStatus.CREATED);
    }
}
