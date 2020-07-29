package com.joseclaudiosiqueira.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joseclaudiosiqueira.springboot.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
	
}

