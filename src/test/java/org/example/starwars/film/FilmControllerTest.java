package org.example.starwars.film;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilmControllerTest {

    @Test
    public void testGetAllFilms_whenFilmsExist_returnsFilmList() throws Exception {

        FilmService mockFilmService = Mockito.mock(FilmService.class);

        List<Film> expectedFilms = new ArrayList<>();
        expectedFilms.add(new Film());
        Mockito.when(mockFilmService.getAllFilms()).thenReturn(expectedFilms);


        FilmController controller = new FilmController();
        controller.filmService = mockFilmService;

        List<Film> actualFilms = controller.getAllFilms();

        assertEquals(expectedFilms, actualFilms);
        Mockito.verify(mockFilmService).getAllFilms();
    }

    @Test
    public void testGetFilmsOfSaga_withExistingSaga_returnsFilms() throws Exception {
        FilmService mockFilmService = Mockito.mock(FilmService.class);

        String saga = "Skywalker";
        List<Film> expectedFilms = new ArrayList<>();
        expectedFilms.add(new Film());
        Mockito.when(mockFilmService.getFilmsOfSaga(saga)).thenReturn(expectedFilms);

        FilmController controller = new FilmController();
        controller.filmService = mockFilmService;

        ResponseEntity<List<Film>> response = controller.getFilmsOfSaga(saga);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedFilms, response.getBody());
        Mockito.verify(mockFilmService).getFilmsOfSaga(saga);
    }


}