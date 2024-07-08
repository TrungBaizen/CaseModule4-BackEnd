package com.example.service.impl;

import com.example.model.DTO.OrderAdminDTO;
import com.example.repository.OrderDetailRepository;
import com.example.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public List<OrderAdminDTO> findOrderDetailsWithTotals(Pageable pageable) {
        return orderDetailRepository.findOrderDetailsWithTotals(pageable);
    }
}
