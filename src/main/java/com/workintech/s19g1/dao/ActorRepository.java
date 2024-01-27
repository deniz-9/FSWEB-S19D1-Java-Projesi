package com.workintech.s19g1.dao;

import com.workintech.s19g1.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor,Integer> {
}
