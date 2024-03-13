package org.example.starwars.film;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Film {

    @JsonProperty("episode_id")
    private String episodeId;
    private String title;
    private String director;
    private String producer;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("opening_crawl")
    private String openingCrawl;
    private List<String> characters;
    private List<String> planets;
    private List<String> starships;
    private List<String> vehicles;
    private List<String> species;
    private String created;
    private String edited;
    private String url;

}
