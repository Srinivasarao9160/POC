package com.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.modeal.Orders;

public interface OrdersDao extends JpaRepository<Orders, Integer> {

}
