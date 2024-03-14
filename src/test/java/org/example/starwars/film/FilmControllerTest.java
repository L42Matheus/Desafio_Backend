package org.example.starwars.film;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

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
        verify(mockFilmService).getFilmsOfSaga(saga);
    }

    @Test
    public void testGetFilmDetails_withExistingId_returnsFilm() throws Exception {
        FilmService mockFilmService = Mockito.mock(FilmService.class);

        Long id = 1L;
        Film expectedFilm = new Film();
        expectedFilm.setId(id);
        Mockito.when(mockFilmService.getDetailFilmById(id)).thenReturn(expectedFilm);

        FilmController controller = new FilmController();
        controller.filmService = mockFilmService;
        ResponseEntity<Film> response = controller.getFilmDetails(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedFilm, response.getBody());
        verify(mockFilmService).getDetailFilmById(id);
    }



    @Test
    public void testUpdateFilmDescription_withValidData_returnsSuccess() throws Exception {
        FilmService mockFilmService = Mockito.mock(FilmService.class);

        Long id = 1L;
        String description = "New description";
        Mockito.doNothing().when(mockFilmService).updateFilmDesccription(id, description);

        FilmController controller = new FilmController();
        controller.filmService = mockFilmService;

        ResponseEntity<String> response = controller.updateFilmDescription(id, description);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Descrição do filme atualizada com sucesso.", response.getBody());
        verify(mockFilmService).updateFilmDesccription(id, description);
    }
}