package org.example.starwars.film;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

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