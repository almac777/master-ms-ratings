package at.ac.fhcampus.master.micro.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto implements Serializable {
    private Long id;
    private Long objectivityRating;
    private Long completionRating;
    private Long userId;
    private Long articleId;
}
