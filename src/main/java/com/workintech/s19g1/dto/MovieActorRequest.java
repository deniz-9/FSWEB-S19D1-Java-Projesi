package com.workintech.s19g1.dto;

import com.workintech.s19g1.entity.Actor;
import com.workintech.s19g1.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieActorRequest {
    private Movie movie;
    private Actor actor;
}
