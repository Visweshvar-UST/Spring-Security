package com.demo.monolithic_spring_security_product_demo.controller;

import java.util.List;

import com.demo.monolithic_spring_security_product_demo.service.ProductService;
import com.demo.monolithic_spring_security_product_demo.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService productService;

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    // @PreAuthorize("hasAuthority(‘ROLE_USER’)")
    @GetMapping("/products/{id}")
    public Product getAProduct(@PathVariable("id") int id){
        return productService.getAProduct(id);
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/products")
    public Product addAProduct(@RequestBody Product product){
        return productService.addAProduct(product);
    }

    @GetMapping
    public String welcome(){
        return "<h1>Hello Visweshvar</h1>";
    }
}
