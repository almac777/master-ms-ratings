package at.ac.fhcampus.master.micro.ratings.services;

import at.ac.fhcampus.master.micro.shared.RatingDto;

import java.util.List;

public interface RatingService {
    List<RatingDto> byArticle(Long article);

    List<RatingDto> byUser(Long user);

    RatingDto rate(RatingDto ratingDto);

    List<RatingDto> list();

    RatingDto update(RatingDto ratingDto, Long id);

    void delete(Long id);
}
