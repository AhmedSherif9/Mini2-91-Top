package com.example.demo.repositories;

import com.example.demo.models.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {
    List<Rating> findByEntityIdAndEntityType(Long entityId, String entityType);
    List<Rating> findByScoreGreaterThan(Integer score);
}
