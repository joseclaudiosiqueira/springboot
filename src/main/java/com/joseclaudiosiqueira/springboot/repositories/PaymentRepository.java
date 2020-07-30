package com.joseclaudiosiqueira.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joseclaudiosiqueira.springboot.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
}

