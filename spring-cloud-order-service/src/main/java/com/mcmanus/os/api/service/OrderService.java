package com.mcmanus.os.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcmanus.os.api.common.Payment;
import com.mcmanus.os.api.common.TransactionRequest;
import com.mcmanus.os.api.common.TransactionResponse;
import com.mcmanus.os.api.model.Order;
import com.mcmanus.os.api.persistence.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    @Lazy
    private RestTemplate template;

    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL;

    public TransactionResponse save(TransactionRequest request) {
        String message;
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());

        log.info("OrderService request : {}", request);

        Payment paymentResponse = template.postForObject(ENDPOINT_URL, payment, Payment.class);

        log.info("PaymentService response from OrderService Rest Call : {}", paymentResponse);

        assert paymentResponse != null;

        message = paymentResponse.getStatus().equals("success")
                ? "Payment processing successful and order placed"
                : "There is a failure while in Payment API";

        repository.save(order);
        return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), message);
    }
}
