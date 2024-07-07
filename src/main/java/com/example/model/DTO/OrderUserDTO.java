package com.example.model.DTO;

import java.time.LocalDateTime;

public interface OrderUserDTO {
    String getName();
    Double getPrice();
    Integer getQuantity();
    Double getTotal();
    LocalDateTime getOrderDate();
}
