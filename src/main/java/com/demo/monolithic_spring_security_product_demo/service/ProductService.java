package com.demo.monolithic_spring_security_product_demo.service;

import java.util.List;

import com.demo.monolithic_spring_security_product_demo.dao.ProductDao;
import com.demo.monolithic_spring_security_product_demo.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;

    public List<Product> getAllProducts(){
        return productDao.findAll();
    }

    public Product getAProduct(Integer id){
        return productDao.findById(id).get();
    }

    public Product addAProduct(Product product){
        return productDao.saveAndFlush(product);
    }
}
