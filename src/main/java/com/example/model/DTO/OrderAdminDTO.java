package com.example.model.DTO;

import java.time.LocalDateTime;

public interface OrderAdminDTO {
    String getName();
    Double getPrice();
    Integer getQuantity();
    Double getTotal();
    LocalDateTime getOrderDate();
    Double getAllTotal();
    String getUsername();
}
