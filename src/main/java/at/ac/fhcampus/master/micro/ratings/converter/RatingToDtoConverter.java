package at.ac.fhcampus.master.micro.ratings.converter;

import at.ac.fhcampus.master.micro.shared.RatingDto;
import at.ac.fhcampus.master.micro.ratings.entities.Rating;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RatingToDtoConverter implements Converter<Rating, RatingDto> {

    @Override
    public RatingDto convert(Rating source) {
        return RatingDto.builder()
                .id(source.getId())
                .completionRating(source.getCompletionRating())
                .objectivityRating(source.getObjectivityRating())
                .articleId(source.getArticleId())
                .userId(source.getUserId())
                .build();
    }

}
