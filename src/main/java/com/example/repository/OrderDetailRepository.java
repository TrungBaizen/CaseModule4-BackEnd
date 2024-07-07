package com.example.repository;

import com.example.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query("SELECT od FROM OrderDetail od WHERE od.order.user.id = :userId AND od.product.id = :productId")
    Optional<OrderDetail> findByOrderIdAndProductId(Long userId, Long productId);
}
