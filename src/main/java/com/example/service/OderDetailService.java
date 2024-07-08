package com.example.service;

import org.springframework.validation.BindingResult;

public interface OderDetailService {
    void addOrder(Long userId, Long productId, Integer quantity , BindingResult bindingResult);
}
