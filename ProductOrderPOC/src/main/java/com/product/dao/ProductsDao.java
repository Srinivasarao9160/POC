package com.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.modeal.Products;

public interface ProductsDao extends JpaRepository<Products, Integer> {

}
