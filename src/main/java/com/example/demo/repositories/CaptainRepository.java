package com.example.demo.repositories;

import com.example.demo.models.Captain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaptainRepository extends JpaRepository<Captain, Long> {

    @Query("SELECT c FROM Captain c WHERE c.avgRatingScore > :ratingThreshold")
    List<Captain> findCaptainsByRatingThreshold(@Param("ratingThreshold") Double ratingThreshold);

    @Query("SELECT c FROM Captain c WHERE c.licenseNumber = :licenseNumber")
    Captain findCaptainByLicenseNumber(@Param("licenseNumber") String licenseNumber);
}


