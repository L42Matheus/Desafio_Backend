package org.example.starwars.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FilmService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FilmeRepository filmeRepository;

    private static final String URL = "https://swapi.info/api/films";

    private Map<String, Film> filmDatabase = new HashMap<>();


    public List<Film> getAllFilms() {
        ResponseEntity<Film[]> response = restTemplate.getForEntity(URL, Film[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Film[] films = response.getBody();

            if (films != null && films.length > 0) {
                filmeRepository.saveAll(Arrays.asList(films));
                return Arrays.asList(films);
            }
            throw new RuntimeException("Lista de filmes está vazia ou nula");
        }
        throw new RuntimeException("Ocorreu um erro ao tentar chamar API externa!");
    }

    public void updateFilmDesccription(String id, String description) throws IllegalAccessException {
        Film film = filmDatabase.get(id);

        if (film != null) {
            film.setOpeningCrawl(description);
            filmDatabase.put(id, film);
        }else {
            throw new IllegalAccessException("Filme cocm ID " + id + " não encontrado.");
        }
    }


    public List<Film> getFilmsOfSaga(String saga) {
        return filmeRepository.findByTitleContainingIgnoreCase(saga);
    }
    public Film getDetailFilmById(String id) {

        String filmUrl = URL + "/" + id;

        ResponseEntity<Film> response = restTemplate.getForEntity(filmUrl, Film.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Não foi possível obter detalhes do filme com ID " + id);
        }
    }
}
