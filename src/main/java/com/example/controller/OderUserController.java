package com.example.controller;

import com.example.service.impl.OrderServiceImpl;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OderUserController {
    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/add")
    public ResponseEntity<String> addOrder(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam @Valid Integer quantity,
            BindingResult bindingResult) {

        try {
            orderService.addOrder(userId, productId, quantity, bindingResult);
            return new ResponseEntity<>("Order added successfully", HttpStatus.OK);
        } catch (ExceptionController.ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add order", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
