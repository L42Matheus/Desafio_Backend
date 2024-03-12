package org.example.starwars.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FilmService {

    @Autowired
    private RestTemplate restTemplate;

    public List<Film> getAllFilms() {
        String url = "https://swapi.info/api/films";

        ResponseEntity<Film[]> response = restTemplate.getForEntity(url, Film[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Film[] films = response.getBody();

            if (films != null && films.length > 0) {

                return Arrays.asList(films);
            }

            throw new RuntimeException("Lista vazia ou nula");

        }

    }
}
