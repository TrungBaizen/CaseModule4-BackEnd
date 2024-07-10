package com.example.repository;

import com.example.model.DTO.OrderAdminDTO;
import com.example.model.OrderDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query(value = "select * from order_detail od join orders o on od.order_id = o.id join Product p on od.product_id = p.id join User u on o.user_id = u.id WHERE o.user_id = :userId AND od.product_id = :productId", nativeQuery = true)
    OrderDetail findByOrderIdAndProductId(Long userId, Long productId);

    @Query(value = "SELECT od.id, p.name AS productName, p.price, od.quantity, od.total, o.order_date AS orderDate, u.username AS userName " +
            "FROM order_detail od " +
            "JOIN product p ON od.product_id = p.id " +
            "JOIN orders o ON od.order_id = o.id " +
            "JOIN user u ON o.user_id = u.id " +
            "ORDER BY o.order_date ASC", nativeQuery = true)
    List<OrderAdminDTO> findOrderDetailsWithTotals();

    @Modifying
    @Transactional
    @Query(value = "CALL delete_order_by_order_detail_id(:orderDetailId)", nativeQuery = true)
    void deleteOrderByOrderDetailId(Long orderDetailId);


    @Query(value = "CALL get_order_details_by_username(:username)", nativeQuery = true)
    List<OrderAdminDTO> getOrderDetailsByUsername(String username);

    @Query(value = "CALL get_total_amount_by_username(:username)", nativeQuery = true)
    Double getTotalAmountByUsername(String username);
}
