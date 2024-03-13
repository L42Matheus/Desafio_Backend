package org.example.starwars.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("swapi/api/films")
public class FilmController {

    @Autowired
    FilmService filmService;

    @GetMapping
    public List<Film> getAllFilms(){
        return filmService.getAllFilms();
    }
}
