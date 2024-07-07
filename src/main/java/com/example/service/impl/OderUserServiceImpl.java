package com.example.service.impl;

import com.example.controller.ExceptionController;
import com.example.model.Order;
import com.example.model.OrderDetail;
import com.example.model.Product;
import com.example.model.User;
import com.example.repository.OrderDetailRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.service.OderService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OderUserServiceImpl implements OderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Transactional
    public void addOrder(Long userId, Long productId, @Min(value = 1, message = "Quantity must be at least 1") Integer quantity) {
        Optional<Product> productOptional = productRepository.findById(productId);

        if (!productOptional.isPresent()) {
            throw new ExceptionController.ResourceNotFoundException("Product not found");
        }

        Product product = productOptional.get();

        // Kiểm tra xem sản phẩm đã có trong giỏ hàng của người dùng chưa
        Optional<OrderDetail> existingOrderDetail = orderDetailRepository.findByOrderIdAndProductId(userId, productId);

        if (existingOrderDetail.isPresent()) {
            // Nếu sản phẩm đã có trong giỏ hàng, cộng thêm số lượng vào
            OrderDetail orderDetail = existingOrderDetail.get();
            int newQuantity = orderDetail.getQuantity() + quantity;
            orderDetail.setQuantity(newQuantity);
            orderDetailRepository.save(orderDetail);

            // Cập nhật lại tổng tiền
            Order order = orderDetail.getOrder();
            order.setTotal(order.getTotal() + (product.getPrice() * quantity));
            orderRepository.save(order);
        } else {
            // Nếu sản phẩm chưa có trong giỏ hàng, tạo một order mới
            Order order = new Order();
            order.setUser(new User(userId)); // Assuming user exists
            order.setOrderDate(LocalDateTime.now());
            order.setTotal(product.getPrice() * quantity);
            orderRepository.save(order);

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            orderDetail.setQuantity(quantity);
            orderDetailRepository.save(orderDetail);
        }
    }
}
