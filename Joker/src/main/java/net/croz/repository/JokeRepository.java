package net.croz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.croz.model.Joke;

public interface JokeRepository extends JpaRepository<Joke, Integer> {
    
}
