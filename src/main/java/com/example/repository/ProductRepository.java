package com.example.repository;

import com.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD

public interface ProductRepository extends JpaRepository<Product, Long> {
=======
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByName(String name);
>>>>>>> 3886351de89193f521cb506ae549872b64ff1f76
}
