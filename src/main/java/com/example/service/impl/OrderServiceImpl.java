package com.example.service.impl;

import com.example.model.DTO.OrderDTO;
import com.example.model.Order;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import com.example.model.OrderDetail;
import com.example.model.Product;
import com.example.model.User;
import com.example.repository.OrderDetailRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private UserRepository userRepository;


    @Transactional
    public void addOrder(OrderDTO orderDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (orderDTO.getQuantity() == null || orderDTO.getQuantity() < 1) {
//                throw new ("Số lượng phải lớn hơn 1");
            }
        }

        Optional<Product> productOptional = productRepository.findById(orderDTO.getProductId());
        // kiểm tra xem sản phẩm tồn tại không
        if (!productOptional.isPresent()) {
//            throw new ("không tồn tại sản phẩm");
        }

        Optional<User> userOptional = userRepository.findById(orderDTO.getId());
        if (!userOptional.isPresent()) {
//            throw new ("không tồn tại người dùng");
        }

        User user = userOptional.get();

        Product product = productOptional.get();

        // Kiểm tra xem sản phẩm đã có trong giỏ hàng của người dùng chưa
        OrderDetail orderDetail = orderDetailRepository.findByOrderIdAndProductId(orderDTO.getId(), orderDTO.getProductId());
        if (orderDetail != null) {
            // nếu orderdetail tồn tại
            Integer newQuantity = orderDetail.getQuantity()+orderDTO.getQuantity();
            orderDetail.setQuantity(newQuantity);
            orderDetail.setTotal(product.getPrice()*newQuantity);
        } else {
            OrderDetail orderDetailNew = new OrderDetail();
            orderDetailNew.setProduct(product);
            orderDetailNew.setQuantity(orderDTO.getQuantity());
            orderDetailNew.setTotal(product.getPrice()*orderDTO.getQuantity());
            orderDetailRepository.save(orderDetailNew);

            // Nếu sản phẩm chưa có trong giỏ hàng, tạo chi tiết đơn hàng mới
            Order order = new Order();
            order.setUser(user);
            order.setOrderDate(LocalDateTime.now());
            orderRepository.save(order);
        }
    }
}

