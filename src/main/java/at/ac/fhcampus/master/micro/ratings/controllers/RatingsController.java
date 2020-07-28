package at.ac.fhcampus.master.micro.ratings.controllers;

import at.ac.fhcampus.master.micro.shared.RatingDto;
import at.ac.fhcampus.master.micro.ratings.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(RatingsController.BASE_URL)
public final class RatingsController {

    static final String BASE_URL = "";
    private static final String ROOT_URL = "/";
    private static final String ROOT_URL_WITH_ID = "/{id}";
    private static final String ARTICLE_RATE_URL = "/by-article/{id}";
    private static final String USER_RATE_URL = "/by-user/{id}";

    private final RatingService ratingService;

    @GetMapping(ARTICLE_RATE_URL)
    public List<RatingDto> byArticle(@PathVariable("id") Long id) {
        return this.ratingService.byArticle(id);
    }

    @GetMapping(USER_RATE_URL)
    public List<RatingDto> byUser(@PathVariable("id") Long id) {
        return this.ratingService.byUser(id);
    }

    @GetMapping(ROOT_URL)
    public List<RatingDto> list() {
        return this.ratingService.list();
    }

    @PostMapping(ROOT_URL)
    public RatingDto rate(@RequestBody RatingDto ratingDto) {
        return this.ratingService.rate(ratingDto);
    }

    @PutMapping(ROOT_URL_WITH_ID)
    @PatchMapping(ROOT_URL_WITH_ID)
    public RatingDto updateRating(@RequestBody RatingDto ratingDto, @PathVariable("id") Long id) {
        return this.ratingService.update(ratingDto, id);
    }

    @DeleteMapping(ROOT_URL_WITH_ID)
    public void deleteRating(@PathVariable("id") Long id) {
        this.ratingService.delete(id);
    }
}
