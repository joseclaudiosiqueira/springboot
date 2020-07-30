package com.joseclaudiosiqueira.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joseclaudiosiqueira.springboot.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	
}

