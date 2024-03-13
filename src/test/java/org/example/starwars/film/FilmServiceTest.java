package org.example.starwars.film;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

class FilmServiceTest {

    @Mock
    private FilmService filmService;

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



    }
}