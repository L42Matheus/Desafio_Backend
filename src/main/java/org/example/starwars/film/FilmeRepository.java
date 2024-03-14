package org.example.starwars.film;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Film, Long> {

    List<Film> findByTitleContainingIgnoreCase(String saga);

}
