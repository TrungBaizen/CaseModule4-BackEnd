package com.example.controller;

import com.example.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order_user")
public class OrderUserController {
    @Autowired
    private OrderService oderDetailService;

    @PostMapping("/add")
    public ResponseEntity<String> addOrder(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam @Valid Integer quantity,
            BindingResult bindingResult) {
        oderDetailService.addOrder(userId, productId, quantity, bindingResult);
        return new ResponseEntity<>("Order added successfully", HttpStatus.OK);
    }
}
