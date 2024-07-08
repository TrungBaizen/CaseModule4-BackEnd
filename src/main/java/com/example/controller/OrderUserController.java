package com.example.controller;

import com.example.service.OderService;
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
    private OderService orderService;

    @PostMapping("/add")
    public ResponseEntity<String> addOrder(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam @Valid Integer quantity,
            BindingResult bindingResult) {

        try {
            orderService.addOrder(userId, productId, quantity, bindingResult);
            return new ResponseEntity<>("Order added successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add order", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
