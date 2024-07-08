package com.example.service.impl;

import com.example.model.Order;
import com.example.model.OrderDetail;
import com.example.model.Product;
import com.example.model.User;
import com.example.repository.OrderDetailRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import com.example.service.OderService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private UserRepository userRepository;


    @Transactional
    public void addOrder(Long userId, Long productId, Integer quantity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (quantity == null || quantity < 1) {
//                throw new ("Số lượng phải lớn hơn 1");
            }
        }

        Optional<Product> productOptional = productRepository.findById(productId);
        // kiểm tra xem sản phẩm tồn tại không
        if (!productOptional.isPresent()) {
//            throw new ("không tồn tại sản phẩm");
        }

        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
//            throw new ("không tồn tại người dùng");
        }

        User user = userOptional.get();

        Product product = productOptional.get();

        // Kiểm tra xem sản phẩm đã có trong giỏ hàng của người dùng chưa
        OrderDetail OrderDetail = orderDetailRepository.findByOrderIdAndProductId(userId, productId);
        if (OrderDetail != null) {
            // nếu orderdetail tồn tại
            Integer newQuantity = OrderDetail.getQuantity()+quantity;
            OrderDetail.setQuantity(newQuantity);
            OrderDetail.setTotal(product.getPrice()*newQuantity);
        } else {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(product);
            orderDetail.setQuantity(quantity);
            orderDetail.setTotal(product.getPrice()*quantity);
            orderDetailRepository.save(orderDetail);

            // Nếu sản phẩm chưa có trong giỏ hàng, tạo chi tiết đơn hàng mới
            Order order = new Order();
            order.setUser(user);
            order.setOrderDate(LocalDateTime.now());
            orderRepository.save(order);
        }
    }
}

