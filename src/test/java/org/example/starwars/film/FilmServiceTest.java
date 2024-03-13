package org.example.starwars.film;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class FilmServiceTest {

    @InjectMocks
    private FilmService filmService;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FilmController filmController;

    @Test
    void getAllFilms() {
        ResponseEntity<Film[]> responseEntity = new ResponseEntity<>(new Film[0], HttpStatus.OK);

        when(restTemplate.getForEntity("https://swapi.info/api/films", Film[].class)).thenReturn(responseEntity);

        List<Film> result = filmController.getAllFilms();

        assertEquals(2, result.size());
        assertEquals("4", result.get(0).getEpisodeId());
        assertEquals("A New Hope", result.get(0).getTitle());
        assertEquals("George Lucas", result.get(0).getDirector());
        assertEquals("5", result.get(1).getEpisodeId());
        assertEquals("The Empire Strikes Back", result.get(1).getTitle());
        assertEquals("Irvin Kershner", result.get(1).getDirector());


    }
}