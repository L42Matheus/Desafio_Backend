package org.example.starwars.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("swapi/api")
public class FilmController {

    @Autowired
    FilmService filmService;





}
