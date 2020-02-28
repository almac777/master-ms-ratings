package at.ac.fhcampus.master.micro.ratings.services;

import at.ac.fhcampus.master.micro.ratings.converter.RatingDtoToEntityConverter;
import at.ac.fhcampus.master.micro.ratings.converter.RatingToDtoConverter;
import at.ac.fhcampus.master.micro.ratings.entities.Rating;
import at.ac.fhcampus.master.micro.shared.RatingDto;
import at.ac.fhcampus.master.micro.ratings.repositories.RatingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultRatingService implements RatingService {

    private final RatingRepository ratingRepository;
    private final RatingToDtoConverter ratingToDtoConverter;
    private final RatingDtoToEntityConverter ratingDtoToEntityConverter;

    private final RabbitTemplate accumulatedRatingsTemplate;
    private final Queue accumulatedRatingsWorkerQueue;

    @Override
    public List<RatingDto> byArticle(Long articleId) {
        return Optional.of(articleId)
                .map(ratingRepository::findByArticleId)
                .orElse(new LinkedList<>())
                .stream()
                .map(ratingToDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<RatingDto> byUser(Long userId) {
        return Optional.of(userId)
                .map(ratingRepository::findByUserId)
                .orElse(new LinkedList<>())
                .stream()
                .map(ratingToDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public RatingDto rate(RatingDto ratingDto) {
        return Optional.of(ratingDto)
                .map(ratingDtoToEntityConverter::convert)
                .map(ratingRepository::save)
                .map(ratingToDtoConverter::convert)
                .map(this::sendRating)
                .orElseThrow(() -> new RuntimeException("Rating has not been saved"));
    }

    private RatingDto sendRating(RatingDto ratingDto) {
        return Optional.of(ratingDto)
                .map(this::printBeforeSend)
                .map(this::sendMessage)
                .orElseThrow(() -> new RuntimeException("Couldn't send rating via RabbitMQ"));
    }

    private RatingDto printBeforeSend(RatingDto rating) {
        log.info("sending rating dto, {}", rating);
        return rating;
    }

    private RatingDto sendMessage(RatingDto rating) {
        Optional.of(rating)
                .map(Object.class::cast)
                .ifPresent(input -> accumulatedRatingsTemplate.convertAndSend(accumulatedRatingsWorkerQueue.getName(), input));
        return rating;
    }

    @Override
    public List<RatingDto> list() {
        return this.ratingRepository.findAll()
                .stream()
                .map(ratingToDtoConverter::convert)
                .collect(Collectors.toList());
    }
}
