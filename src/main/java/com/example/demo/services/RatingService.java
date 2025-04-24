package com.example.demo.services;

import com.example.demo.models.Rating;
import com.example.demo.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }
    public Rating addRating(Rating rating){
      return  ratingRepository.save(rating);
    }
    public Rating updateRating(String id, Rating updatedRating) {
        return ratingRepository.findById(id)
                .map(existingRating -> {
                    existingRating.setEntityId(updatedRating.getEntityId());
                    existingRating.setEntityType(updatedRating.getEntityType());
                    existingRating.setScore(updatedRating.getScore());
                    existingRating.setComment(updatedRating.getComment());
                    existingRating.setRatingDate(updatedRating.getRatingDate() != null
                            ? updatedRating.getRatingDate()
                            : LocalDateTime.now());

                    return ratingRepository.save(existingRating);
                })
                .orElseThrow(() -> new RuntimeException("Rating not found with id: " + id));
    }
    public void deleteRating(String id) {
        if (!ratingRepository.existsById(id)) {
            throw new RuntimeException("Rating not found with id: " + id);
        }
        ratingRepository.deleteById(id);
    }
    public List<Rating> getRatingsByEntity(Long entityId, String entityType){
        return ratingRepository.findByEntityIdAndEntityType(entityId,entityType);
    }
    public List<Rating> findRatingsAboveScore(int minScore){
        return  ratingRepository.findByScoreGreaterThan(minScore);
    }
}
