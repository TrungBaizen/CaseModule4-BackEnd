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

    @Query(value = "SELECT p.id, p.name, p.price, p.image FROM Product p  WHERE p.category_id = 1", nativeQuery = true)
    List<Product> showAllFood();

    @Query(value = "SELECT p.id, p.name, p.price, p.image  FROM Product p WHERE p.category_id = 2", nativeQuery = true)
    List<Product> showAllDrink();


}
