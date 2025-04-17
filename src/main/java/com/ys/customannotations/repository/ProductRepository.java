package com.ys.customannotations.repository;

import com.ys.customannotations.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
