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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
        ResponseEntity<Film[]> responseEntity = new ResponseEntity<>(filmsArray, HttpStatus.OK);
        when(restTemplate.getForEntity("https://swapi.info/api/films", Film[].class)).thenReturn(responseEntity);

        List<Film> films = filmService.getAllFilms();

        assertEquals(2, films.size());
        assertTrue(films.contains(film1));
        assertTrue(films.contains(film2));


    }

    @Test
    void getAllFilms_ThrowsRuntimeException_WhenApiResponseIsEmpty() {

        ResponseEntity<Film[]> responseEntity = new ResponseEntity<>(new Film[0], HttpStatus.OK);
        when(restTemplate.getForEntity("https://swapi.info/api/films", Film[].class)).thenReturn(responseEntity);

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
        String filmId = "1";
        String filmUrl = "https://swapi.info/api/films/" + filmId;
        Film expectedFilm = new Film();

        ResponseEntity<Film> responseEntity = new ResponseEntity<>(expectedFilm, HttpStatus.OK);
        when(restTemplate.getForEntity(filmUrl, Film.class)).thenReturn(responseEntity);

        Film film = filmService.getDetailFilmById(filmId);

        assertNotNull(film);
        assertEquals(expectedFilm, film);
    }

    @Test
    public void testGetDetailFilmById_Failure() {
        String filmId = "999";
        String filmUrl = "https://swapi.info/api/films/" + filmId;
        ResponseEntity<Film> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        when(restTemplate.getForEntity(filmUrl, Film.class)).thenReturn(responseEntity);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> filmService.getDetailFilmById(filmId));

        assertEquals("Não foi possível obter detalhes do filme com ID " + filmId, exception.getMessage());
    }
}