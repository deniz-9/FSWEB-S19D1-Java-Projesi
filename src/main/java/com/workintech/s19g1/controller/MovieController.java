package com.workintech.s19g1.controller;

import com.workintech.s19g1.dto.ActorResponse;
import com.workintech.s19g1.dto.MovieActorRequest;
import com.workintech.s19g1.dto.MovieActorResponse;
import com.workintech.s19g1.dto.MovieResponse;
import com.workintech.s19g1.entity.Actor;
import com.workintech.s19g1.entity.Movie;
import com.workintech.s19g1.service.MovieService;
import com.workintech.s19g1.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping("/")
    public List<MovieResponse> findAll() {
        List<MovieResponse> movieResponses = new ArrayList<>(); // yeni boş array oluşturuldu.
        List<Movie> movieList = movieService.findAll();

        for (Movie movie : movieList) {
            movieResponses.add(new MovieResponse(movie.getId(), movie.getName(), movie.getDirectorName(), movie.getRating(), movie.getReleaseDate()));
        }
        return movieResponses;
    }


    @GetMapping("/{id}")
    public MovieResponse findById(@PathVariable int id) {
        Movie foundMovie = movieService.findById(id);
        return new MovieResponse(foundMovie.getId(), foundMovie.getName(), foundMovie.getDirectorName(), foundMovie.getRating(), foundMovie.getReleaseDate());
    }


    @PostMapping("/")
    public MovieActorResponse save(@RequestBody MovieActorRequest movieActorRequest) {
        Movie movie = movieActorRequest.getMovie();
        Actor actor = movieActorRequest.getActor();
        movie.addActor(actor);
        Movie savedMovie = movieService.save(movie);
        return new MovieActorResponse(movie, actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getBirthDate());
    }

    @PostMapping("/addActor/{movieId}")
    public List<ActorResponse> addActor(@RequestBody List<Actor> actors, @PathVariable int movieId) {
        Movie movie = movieService.findById(movieId);
        movie.addAllActor(actors);
        Movie savedMovie = movieService.save(movie);
        List<ActorResponse> listOfResponse = new ArrayList<>();
        for (Actor actor : actors) {
            listOfResponse.add(new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getBirthDate()));
        }
        return listOfResponse;
    }

    @PutMapping("/{id}")
    public MovieResponse update(@RequestBody Movie movie, @PathVariable int id) {
        Movie foundedMovie = movieService.findById(id);
        movie.setId(id);
        movie.setActorList(foundedMovie.getActorList());
        Movie updatedMovie = movieService.save(movie);
        return new MovieResponse(movie.getId(), movie.getName(), movie.getDirectorName(), movie.getRating(), movie.getReleaseDate());
    }

    @DeleteMapping("/{id}")
    public MovieResponse delete(@PathVariable int id){
        Movie foundedMovie = movieService.findById(id);
        movieService.delete(foundedMovie);
        return new MovieResponse(foundedMovie.getId(),foundedMovie.getName(),foundedMovie.getDirectorName(),foundedMovie.getRating(),foundedMovie.getReleaseDate());
    }


}
