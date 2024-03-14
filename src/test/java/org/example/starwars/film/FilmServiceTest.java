package org.example.starwars.film;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hibernate.cfg.JdbcSettings.URL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilmServiceTest {

    @InjectMocks
    private FilmService filmService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private FilmeRepository filmeRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadFilmsInMemory_Success() {
        Film film1 = new Film();
        film1.setEpisodeId("4");
        film1.setTitle("A New Hope");
        film1.setDirector("George Lucas");

        Film film2 = new Film();
        film2.setEpisodeId("5");
        film2.setTitle("The Empire Strikes Back");
        film2.setDirector("Irvin Kershner");

        Film[] filmsArray = {film1, film2};

        ResponseEntity<Film[]> responseEntity = new ResponseEntity<>(filmsArray, HttpStatus.OK);
        when(restTemplate.getForEntity("https://swapi.info/api/films", Film[].class)).thenReturn(responseEntity);

        filmService.loadFilmsIntoMemory();

        verify(filmeRepository, times(1)).saveAll(Arrays.asList(filmsArray));

    }


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

        Film[] filmsArray = { film1, film2 };

        when(filmeRepository.findAll())
                .thenReturn(List.of(filmsArray));

        List<Film> films = filmService.getAllFilms();

        assertEquals(2, films.size());
        assertTrue(films.contains(film1));
        assertTrue(films.contains(film2));


    }

    @Test
    void getAllFilms_ThrowsRuntimeException_WhenApiResponseIsEmpty() {

        when(filmeRepository.findAll()).thenReturn(Collections.emptyList());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> filmService.getAllFilms());

        assertEquals("Lista de filmes está vazia ou nula", exception.getMessage());
    }


    @Test
    public void testGetFilmsOfSaga() {
        Film film1 = new Film();
        film1.setTitle("Star Wars: Episode IV - A New Hope");

        Film film2 = new Film();
        film2.setTitle("Star Wars: Episode V - The Empire Strikes Back");

        Film[] filmsArray = {film1, film2};

        when(filmeRepository.findByTitleContainingIgnoreCase(ArgumentMatchers.anyString()))
                .thenReturn(List.of(filmsArray));
        List<Film> films = filmService.getFilmsOfSaga("Star Wars");

        assertNotNull(films);
        assertEquals(2, films.size());
        assertTrue(films.stream().allMatch(film -> film.getTitle().contains("Star Wars")));
    }

    @Test
    public void testGetDetailFilmById_Success() {
        Long filmId = 1L;
        Film expectedFilm = new Film();
        expectedFilm.setId(filmId);

        when(filmeRepository.findById(filmId)).thenReturn(Optional.of(expectedFilm));

        Film film = filmService.getDetailFilmById(filmId);

        assertNotNull(film);
        assertEquals(expectedFilm, film);
    }

    @Test
    public void testGetDetailFilmById_Failure() {
        Long filmId = 999L;

        when(filmeRepository.findById(filmId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> filmService.getDetailFilmById(filmId));

        assertEquals("Não foi possível obter detalhes do filme com ID " + filmId, exception.getMessage());
    }
}