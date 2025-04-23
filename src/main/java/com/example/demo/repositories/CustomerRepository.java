package com.example.demo.repositories;

import com.example.demo.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.email LIKE %:domain")
    public List<Customer> findCustomersByEmailDomain(@Param("domain") String domain);

    @Query("SELECT c FROM Customer c WHERE c.phoneNumber LIKE :prefix%")
    public List<Customer> findCustomersByPhonePrefix(@Param("prefix") String prefix);
}
