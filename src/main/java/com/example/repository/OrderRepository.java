package com.example.repository;

import com.example.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD

public interface OrderRepository extends JpaRepository<Order, Long> {
=======
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

>>>>>>> 3886351de89193f521cb506ae549872b64ff1f76
}
