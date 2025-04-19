package com.example.demo.controllers;

import com.example.demo.models.Rating;
import com.example.demo.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RatingController {
    private final RatingService ratingService;
    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }
    @PostMapping("/addRating")
    public Rating addRating(@RequestBody Rating rating){
        return ratingService.addRating(rating);

    }
    @PutMapping("/update/{id}")
    public Rating updateRating(@PathVariable String id, @RequestBody Rating updatedRating){
        return  ratingService.updateRating(id,updatedRating);

    }
    @DeleteMapping("/delete/{id}")
    public String deleteRating(@PathVariable String id) {
        // Call the service to delete the rating
        try {
            ratingService.deleteRating(id); // This will delete the rating by its ID

            // Return a success message if deletion was successful
            return "Rating deleted successfully";
        } catch (Exception e) {
            // Return an error message if there is any issue during deletion
            return "Error: Rating not found or could not be deleted";
        }
    }
    @GetMapping("/findByEntity")
    public List<Rating> findRatingsByEntity(@RequestParam Long entityId, @RequestParam String entityType){
        return  ratingService.getRatingsByEntity(entityId,entityType);
    }
    @GetMapping("/findAboveScore")
    public List<Rating> findRatingsAboveScore(@RequestParam int minScore){
        return ratingService.findRatingsAboveScore(minScore);
    }
}
