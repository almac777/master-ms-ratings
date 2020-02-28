package at.ac.fhcampus.master.micro.ratings.repositories;

import at.ac.fhcampus.master.micro.ratings.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    @Query("FROM Rating r WHERE r.articleId = :id")
    List<Rating> findByArticleId(@Param("id") Long id);

    @Query("FROM Rating r WHERE r.userId = :id")
    List<Rating> findByUserId(@Param("id") Long id);

    Long countAllByArticleId(Long articleId);
}
