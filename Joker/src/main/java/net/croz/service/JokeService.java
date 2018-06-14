package net.croz.service;

import java.util.List;
import java.util.Map;

import net.croz.exception.JokeNotFoundException;
import net.croz.model.Joke;

public interface JokeService {
    
    /**
     * Maps all jokes in repository.
     * Key is joke id.
     * 
     * @return Map of all jokes.
     */
    public Map<Integer, Joke> getAll();
    
    /**
     * Gets joke with {@code id}.
     * 
     * @param id Id of joke
     * @return Desired joke
     * @throws JokeNotFoundException if no joke with that id is found.
     */
    public Joke getOne(Integer id) throws JokeNotFoundException;
    
    /**
     * Saves joke.
     * 
     * @param joke Joke to save.
     * @return Saved joke.
     */
    public Joke create(Joke joke);
    
    /**
     * Adds like to joke.
     * 
     * @param id Id of liked joke.
     * @return Liked Joke.
     * @throws JokeNotFoundException if no joke with that id is found.
     */
    public Joke likeJoke(Integer id) throws JokeNotFoundException;
    
    /**
     * Adds dislike to joke.
     * 
     * @param id Id of disliked joke.
     * @return Disliked Joke.
     * @throws JokeNotFoundException if no joke with that id is found.
     */
    public Joke dislikeJoke(Integer id) throws JokeNotFoundException;
    
    /**
     * Calculates difference of likes and dislikes (n(likes) - n(dislikes)).
     * 
     * @param id Id of Joke.
     * @return Difference of likes and dislikes.
     * @throws JokeNotFoundException if no joke with that id is found.
     */
    public Integer likeDifference(Integer id) throws JokeNotFoundException;
    
    /**
     * Orders jokes based on difference of likes and dislikes.
     * Uses {@link #likeDifference(Integer)} to calculate rank.
     * 
     * @return List of id's, 1st element is best ranked.
     */
    public List<Joke> orderedByDifference();
    
    /**
     * Calculates differences of likes of {@code jokes}.
     * 
     * @param jokes List of jokes
     * @return List of difference of likes
     */
    public List<Integer> getDifferenceList(List<Joke> jokes); 
}
