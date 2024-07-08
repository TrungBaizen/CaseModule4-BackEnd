package com.example.service;

import com.example.model.Order;
import org.springframework.validation.BindingResult;

public interface OrderService {
    void addOrder(Long userId, Long productId, Integer quantity, BindingResult bindingResult);
}
