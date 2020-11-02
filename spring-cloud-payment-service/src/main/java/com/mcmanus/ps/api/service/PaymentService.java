package com.mcmanus.ps.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcmanus.ps.api.model.Payment;
import com.mcmanus.ps.api.persistence.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public Payment save(Payment payment) {
        payment.setStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString() );
        log.info("PaymentService doPayment : {}", payment);
        return repository.save(payment);
    }

    // Should be a 3rd party API (Paypal, ...)
    public String paymentProcessing(){
        return new Random().nextBoolean()  ? "success": "false";
    }

    public Payment getByOrderId(int orderId) {
        Payment payment = repository.findByOrderId(orderId);
        log.info("PaymentService getByOrderId : {}", payment);

        return payment;
    }
}
