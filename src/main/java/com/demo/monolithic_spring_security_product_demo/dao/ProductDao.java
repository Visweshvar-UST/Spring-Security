package com.demo.monolithic_spring_security_product_demo.dao;

import com.demo.monolithic_spring_security_product_demo.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product,Integer> {
    
}
