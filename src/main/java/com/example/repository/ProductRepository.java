package com.example.repository;

import com.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByNameContaining(String name);
    boolean existsProductByName(String name);

    @Query(value = "SELECT p.id, p.name, p.price, p.image FROM Product p JOIN Category c ON p.category_id = c.id WHERE c.name LIKE 'đồ ăn'", nativeQuery = true)
    List<Product> showAllFood();

    @Query(value = "SELECT p.id, p.name, p.price, p.image  FROM Product p JOIN Category c ON p.category_id = c.id WHERE c.name LIKE 'đồ uống'", nativeQuery = true)
    List<Product> showAllDrink();

}
