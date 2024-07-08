package com.example.repository;

import com.example.model.DTO.OrderAdminDTO;
import com.example.model.OrderDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query(value = "select od.id , p.name as product_name , u.name as user_name from Orderdetail od join Orders o on od.order_id = o.id join Product p on od.product_id = p.id join User u on o.user_id = u.id", nativeQuery = true)
    OrderDetail findByOrderIdAndProductId(Long userId, Long productId);

    @Query(value = "SELECT od.id , p.name as product_name, p.price, od.quantity, od.total, o.orderDate, u.username as user_name from OderDetail od JOIN Product p ON od.product_id = p.id JOIN Oder o ON od.order_id = o.id JOIN User u ON o.user_id = u.id ORDER BY o.orderDate ASC", nativeQuery = true)
    List<OrderAdminDTO> findOrderDetailsWithTotals(Pageable pageable);

//    @Query(value = "SELECT * FROM" , nativeQuery = true)
}
