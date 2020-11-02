package com.mcmanus.ps.api.controller;

import com.mcmanus.ps.api.model.Payment;
import com.mcmanus.ps.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping
    public Payment save(@RequestBody Payment payment) {
        return service.save(payment);
    }

    @GetMapping("/{orderId}")
    public Payment getByOrderId(@PathVariable int orderId) {
        return service.getByOrderId(orderId);
    }

}
