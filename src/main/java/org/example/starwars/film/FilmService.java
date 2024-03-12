package org.example.starwars.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FilmService {

    @Autowired
    private RestTemplate restTemplate;

    public List<Film> getAllFilms() {
        String url = "https://swapi.info/api/films";
    }
}
