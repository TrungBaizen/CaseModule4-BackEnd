package com.example.repository;

import com.example.model.DTO.OrderAdminDTO;
import com.example.model.OrderDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query(value = "SELECT * FROM OrderDetail od WHERE od.order.user.id = :userId AND od.product.id = :productId", nativeQuery = true)
    OrderDetail findByOrderIdAndProductId(Long userId, Long productId);

    @Query(value = "SELECT od.id , p.name as product_name, p.price, od.quantity, od.total, o.orderDate, u.username as user_name from OderDetail od JOIN Product p ON od.product_id = p.id JOIN Oder o ON od.order_id = o.id JOIN User u ON o.user_id = u.id ORDER BY o.orderDate ASC", nativeQuery = true)
    List<OrderAdminDTO> findOrderDetailsWithTotals(Pageable pageable);

//    @Query(value = "SELECT * FROM" , nativeQuery = true)


}
