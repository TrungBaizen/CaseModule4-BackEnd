package com.example.model.DTO;

import java.time.LocalDateTime;

public interface OrderAdminDTO {
    Long getId();
    String getName();
    Double getPrice();
    Integer getQuantity();
    Double getTotal();
    LocalDateTime getOrderDate();
    String getUsername();
}
