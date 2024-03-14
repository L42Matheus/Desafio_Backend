package org.example.starwars.film;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("episode_id")
    private String episodeId;
    private String title;
    private String director;
    private String producer;
    @JsonProperty("release_date")
    private String releaseDate;

    @Column(length = 2000)
    @JsonProperty("opening_crawl")
    private String openingCrawl;
    @Column(length = 2000)
    private List<String> characters;
    @Column(length = 2000)
    private List<String> planets;
    @Column(length = 2000)
    private List<String> starships;
    @Column(length = 2000)
    private List<String> vehicles;
    @Column(length = 2000)
    private List<String> species;
    private String created;
    private String edited;
    private String url;

}
