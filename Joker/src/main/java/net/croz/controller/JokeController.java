package net.croz.controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import net.croz.exception.JokeNotFoundException;
import net.croz.model.Joke;
import net.croz.model.JokeForm;
import net.croz.service.JokeService;

@Controller
public class JokeController {
    
    @Autowired
    private JokeService jokeService;
    
    @GetMapping("/")
    public String getAll(Model model) throws JokeNotFoundException {
        List<Joke> jokes = jokeService.orderedByDifference();
        model.addAttribute("jokes", jokes);
        model.addAttribute("difference", jokeService.getDifferenceList(jokes));
        return "index";
    }
    
    @PostMapping("/like")
    public String likeOne(@RequestBody String idRaw) throws JokeNotFoundException {
        Integer id = Integer.parseInt(idRaw.split("=")[0]);
        jokeService.likeJoke(id);
        return "redirect";
    }
    
    @PostMapping("/dislike")
    public String dislikeOne(@RequestBody String idRaw) throws JokeNotFoundException {
        Integer id = Integer.parseInt(idRaw.split("=")[0]);
        jokeService.dislikeJoke(id);
        return "redirect";
    }
    
    @PostMapping("/new")
    public String jokeSubmit(@ModelAttribute(name = "joke") JokeForm jokeForm) {
        Joke newJoke = Joke.builder()
                .content(jokeForm.getContent())
                .dislikes(0)
                .likes(0)
                .build();
        jokeService.create(newJoke);
        return "result";
    }
    
    @GetMapping("/new")
    public String jokeForm(Model model) {
        model.addAttribute("joke", new JokeForm());
        return "joke";
    }
    
    
}
