package com.example.controller;

import com.example.model.DTO.OrderAdminDTO;
import com.example.service.OrderDetailService;
import com.example.service.impl.OrderDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order_admin")
public class OrderAdminController {
    @Autowired
    private OrderDetailService orderDetailService;
    @GetMapping(value = "/list")
    public ResponseEntity<List<OrderAdminDTO>> getOrderAdminList(@PageableDefault(value = 5) Pageable pageable) {
        List<OrderAdminDTO> orderAdminList = orderDetailService.findOrderDetailsWithTotals(pageable);
        return ResponseEntity.ok(orderAdminList);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteOrderDetail(@PathVariable Long id) {
        orderDetailService.deleteOrderByOrderDetailId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/by_username/{username}")
    public ResponseEntity<List<OrderAdminDTO>> getOrderDetailsByUsername(@PathVariable String username) {
        List<OrderAdminDTO> orderDetailsByUsername = orderDetailService.getOrderDetailsByUsername(username);
        return ResponseEntity.ok(orderDetailsByUsername);
    }
}
