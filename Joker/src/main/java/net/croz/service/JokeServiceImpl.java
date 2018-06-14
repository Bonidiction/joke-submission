package net.croz.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.croz.exception.JokeNotFoundException;
import net.croz.model.Joke;
import net.croz.repository.JokeRepository;

@Service
public class JokeServiceImpl implements JokeService {

    @Autowired
    private JokeRepository repository;
    
    @Override
    public Map<Integer, Joke> getAll() {
        List<Joke> allJokes = repository.findAll();
        Map<Integer, Joke> result = new HashMap<>(allJokes.size());
        for (Joke joke : allJokes) {
            result.put(joke.getId(), joke);
        }
        return result;
        
    }

    @Override
    public Joke create(Joke joke) {
        return repository.save(joke);
    }

    @Override
    public Joke likeJoke(Integer id) throws JokeNotFoundException {
        Joke joke =  getOne(id);
        joke.setLikes(joke.getLikes() + 1);
        return repository.save(joke);
    }

    @Override
    public Joke dislikeJoke(Integer id) throws JokeNotFoundException {
        Joke joke =  getOne(id);
        joke.setDislikes(joke.getDislikes() + 1);
        return repository.save(joke);
    }

    @Override
    public Integer likeDifference(Integer id) throws JokeNotFoundException {
        Joke joke =  getOne(id);
        return joke.getLikes() - joke.getDislikes();
    }

    @Override
    public List<Joke> orderedByDifference() {
        List<Joke> jokes = repository.findAll();
        Collections.sort(jokes, (first, other) -> {
            if (first.equals(other)) {
                return 0;
            } else {
                try {
                    if (likeDifference(first.getId()) < likeDifference(other.getId())) {
                        return 1;
                    } else {
                        return -1;
                    }
                } catch (JokeNotFoundException e) {
                    System.err.println("Something went terribly wrong in orderByDifference()");
                    e.printStackTrace();
                    throw new Error("Something went terribly wrong in orderByDifference()");
                }
            }
        });
        return jokes;
        /*List<Integer> idList = new ArrayList<>(jokes.size());
        for (Joke joke : jokes) {
            idList.add(joke.getId());
        }
        return idList;*/ //artifact, should be removed
    }

    @Override
    public Joke getOne(Integer id) throws JokeNotFoundException {
        Optional<Joke> optJoke = repository.findById(id);
        if (!optJoke.isPresent()) {
            throw new JokeNotFoundException(String.valueOf(id));
        }
        return optJoke.get();
    }

    @Override
    public List<Integer> getDifferenceList(List<Joke> jokes) {
        List<Integer> differences = new ArrayList<>(jokes.size());
        for (Joke joke : jokes) {
            try {
                differences.add(likeDifference(joke.getId()));
            } catch (JokeNotFoundException e) {
                System.err.println("Something went terribly wrong in getDifferenceList()");
                e.printStackTrace();
                throw new Error("Something went terribly wrong in getDifferenceList()");
            }
        }
        return differences;
    }

}
