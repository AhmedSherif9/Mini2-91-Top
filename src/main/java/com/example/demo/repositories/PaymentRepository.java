package com.example.demo.repositories;

import com.example.demo.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT p FROM Payment p WHERE p.trip.id = :tripId")
    public List<Payment> findPaymentsByTripId(@Param("tripId") long tripId);

    @Query("SELECT p FROM Payment p WHERE p.amount > :threshold")
    public List<Payment> findPaymentsAboveThreshold(@Param("threshold") double threshold);

}
