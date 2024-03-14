package org.example.starwars.film;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class FilmService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FilmeRepository filmeRepository;

    private static final String URL = "https://swapi.info/api/films";

    private Map<String, Film> filmDatabase = new HashMap<>();

    @PostConstruct
    public void init() {
        loadFilmsIntoMemory();
    }

    protected void loadFilmsIntoMemory() {
        ResponseEntity<Film[]> response = restTemplate.getForEntity(URL, Film[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Film[] films = response.getBody();

            if (films != null && films.length > 0) {
                filmeRepository.saveAll(Arrays.asList(films));
            } else {
                throw new RuntimeException("Lista de filmes está vazia ou nula");
            }
        } else {
            throw new RuntimeException("Ocorreu um erro ao tentar chamar a API externa!");
        }
    }


    public List<Film> getAllFilms() {
        List<Film> films = filmeRepository.findAll();
        if (!films.isEmpty()) {
            return films;
        } else {
            throw new RuntimeException("Lista de filmes está vazia ou nula");
        }
    }

    public void updateFilmDesccription(Long id, String description) throws IllegalAccessException {
        Film film = filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possível obter detalhes do filme com ID " + id));

        if (film != null) {
            film.setOpeningCrawl(description);
            film.setEdited(new Date().toString());
            film.setVersion(film.getVersion() + 1);
            filmeRepository.save(film);
        }else {
            throw new IllegalAccessException("Filme cocm ID " + id + " não encontrado.");
        }
    }


    public List<Film> getFilmsOfSaga(String saga) {
        return filmeRepository.findByTitleContainingIgnoreCase(saga);
    }
    public Film getDetailFilmById(Long id) {
        return filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possível obter detalhes do filme com ID " + id));
    }
}
