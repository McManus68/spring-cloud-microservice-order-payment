package com.mcmanus.os.api.controller;

import com.mcmanus.os.api.common.Payment;
import com.mcmanus.os.api.common.TransactionRequest;
import com.mcmanus.os.api.common.TransactionResponse;
import com.mcmanus.os.api.model.Order;
import com.mcmanus.os.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public TransactionResponse save(@RequestBody TransactionRequest request) {
        return service.save(request);
    }
}
