package com.example.demo.services;

import com.example.demo.models.Payment;
import com.example.demo.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    public Payment addPayment(Payment payment) {return paymentRepository.save(payment);}

    public List<Payment> getAllPayments() {return paymentRepository.findAll();}

    public Payment getPaymentById(Long id) {
        Optional<Payment> OptionalPayment = paymentRepository.findById(id);
        if (OptionalPayment.isPresent()) {
            return OptionalPayment.get();
        } else {
            throw new RuntimeException("Payment not found");
        }

    }

    public Payment updatePayment(Long id, Payment payment) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isPresent()) {
            Payment existingPayment = optionalPayment.get();
            existingPayment.setAmount(payment.getAmount());
            existingPayment.setPaymentMethod(payment.getPaymentMethod());
            existingPayment.setPaymentStatus(payment.isPaymentStatus());
            return paymentRepository.save(existingPayment);
        } else {
            throw new RuntimeException("Payment with id " + id + " not found");
        }
    }

    public List<Payment> findPaymentsByTripId(Long tripId) {
        return paymentRepository.findPaymentsByTripId(tripId);
    }

    public List<Payment> findByAmountThreshold(Double threshold) {
        return paymentRepository.findPaymentsAboveThreshold(threshold);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

}
