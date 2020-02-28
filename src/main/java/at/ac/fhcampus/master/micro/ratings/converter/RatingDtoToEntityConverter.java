package at.ac.fhcampus.master.micro.ratings.converter;

import at.ac.fhcampus.master.micro.shared.RatingDto;
import at.ac.fhcampus.master.micro.ratings.entities.Rating;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RatingDtoToEntityConverter implements Converter<RatingDto, Rating> {
    @Override
    public Rating convert(RatingDto source) {
        return Rating.builder()
                .id(source.getId())
                .objectivityRating(source.getObjectivityRating())
                .completionRating(source.getCompletionRating())
                .articleId(source.getArticleId())
                .userId(source.getUserId())
                .build();
    }

}
