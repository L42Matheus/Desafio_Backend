package org.example.starwars.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("swapi/api/films")
public class FilmController {

    @Autowired
    FilmService filmService;

    @GetMapping
    public List<Film> getAllFilms(){
        return filmService.getAllFilms();
    }

    @GetMapping("/saga/{saga}")
    public ResponseEntity<List<Film>> getFilmsOfSaga(@PathVariable String saga) {
        List<Film> films = filmService.getFilmsOfSaga(saga);
        if (!films.isEmpty()) {
            return ResponseEntity.ok(films);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmDetails(@PathVariable Long id) {
        Film film = filmService.getDetailFilmById(id);
        if (film != null) {
            return ResponseEntity.ok(film);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}/description")
    public ResponseEntity<String> updateFilmDescription(@PathVariable Long id, @RequestBody String description) {
        try {
            filmService.updateFilmDesccription(id, description);
            return ResponseEntity.ok("Descrição do filme atualizada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
