package org.example.starwars.film;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

class FilmServiceTest {

    @Mock
    private FilmService filmService;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FilmController filmController;

    @Test
    void getAllFilms() {

        Film film1 = new Film();
        film1.setEpisodeId("4");

        film1.setTitle("A New Hope");
        film1.setDirector("George Lucas");

        Film film2 = new Film();
        film2.setEpisodeId("5");

        film2.setTitle("The Empire Strikes Back");
        film2.setDirector("Irvin Kershner");

        List<Film> films = Arrays.asList(film1, film2);

        when(filmService.getAllFilms()).thenReturn(films);

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