package com.workintech.s19g1.entity;

import com.workintech.s19g1.service.ActorService;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "movie", schema = "fsweb")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "director_name")
    private String directorName;

    @Column(name = "rating")
    private int rating;

    @Column(name = "release_date")
    private LocalDate releaseDate;


    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "movie_actor",schema = "fsweb",joinColumns = @JoinColumn(name = "movie_id") ,
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actorList;

    public void addActor(Actor actor){
        if(actorList == null){
            actorList=new ArrayList<>();
        }
        actorList.add(actor);
    }


    public void addAllActor(List<Actor> actors){
        if(actorList == null){
            actorList=new ArrayList<>();
        }
        actorList.addAll(actors);
    }

}

