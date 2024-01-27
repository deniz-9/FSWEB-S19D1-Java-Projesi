package com.workintech.s19g1.service;

import com.workintech.s19g1.entity.Actor;
import com.workintech.s19g1.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MovieService {

    List<Movie> findAll();

    Movie findById(int id);

    Movie save(Movie movie);

    void delete(Movie movie);
}
