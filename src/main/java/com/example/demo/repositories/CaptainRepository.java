package com.example.demo.repositories;

import com.example.demo.models.Captain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaptainRepository extends JpaRepository<Captain, Long> {
}
