package org.example.starwars.film;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

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

}
