package com.workintech.s19g1.dao;

import com.workintech.s19g1.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
}
