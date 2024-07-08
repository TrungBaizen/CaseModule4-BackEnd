package com.example.service;

import com.example.model.DTO.OrderAdminDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderDetailService {
    List<OrderAdminDTO> findOrderDetailsWithTotals(Pageable pageable);

    void deleteOrderByOrderDetailId(Long orderDetailId);

    List<OrderAdminDTO> getOrderDetailsByUsername(String username , Pageable pageable);
}
