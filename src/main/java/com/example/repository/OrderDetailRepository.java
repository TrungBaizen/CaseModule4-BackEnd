package com.example.repository;

import com.example.model.DTO.OrderAdminDTO;
import com.example.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query(value = "SELECT * FROM OrderDetail od WHERE od.order.user.id = :userId AND od.product.id = :productId", nativeQuery = true)
    OrderDetail findByOrderIdAndProductId(Long userId, Long productId);

    @Query(value = "SELECT p.name, p.price, od.quantity, od.total, o.orderDate, u.username, SUM(od.total) from OderDetail od JOIN Product p on od.) ", nativeQuery = true)
    List<OrderAdminDTO> findOrderDetailsWithTotals();
}
