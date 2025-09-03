package com.ecommerce.sb_ecomm.repository;

import com.ecommerce.sb_ecomm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {
}
