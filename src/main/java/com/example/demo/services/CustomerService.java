package com.example.demo.services;

import com.example.demo.models.Customer;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer){return customerRepository.save(customer);}

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        }
        else {
            throw new RuntimeException("Customer not found");
        }
    }
    public Customer updateCustomer(Long id, Customer customer){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
          Customer  existingCustomer = optionalCustomer.get();
          existingCustomer.setName(customer.getName());
          existingCustomer.setEmail(customer.getEmail());
          existingCustomer.setPhoneNumber(customer.getPhoneNumber());
          existingCustomer.setTrips(customer.getTrips());
          return customerRepository.save(existingCustomer);
        } else {
            throw new RuntimeException("Customer with id " + id + " not found");
        }
    }

    public List<Customer> findCustomersByEmailDomain(String domain){
        if (!domain.startsWith("@")) {
            domain = "@" + domain;
        }
        return customerRepository.findCustomersByEmailDomain(domain);
    }
    public List<Customer> findCustomersByPhonePrefix(String prefix) {
        return customerRepository.findCustomersByPhonePrefix(prefix);
    }


    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
