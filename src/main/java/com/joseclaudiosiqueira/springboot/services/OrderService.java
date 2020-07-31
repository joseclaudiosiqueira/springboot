package com.joseclaudiosiqueira.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joseclaudiosiqueira.springboot.domain.Order;
import com.joseclaudiosiqueira.springboot.repositories.OrderRepository;
import com.joseclaudiosiqueira.springboot.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;

	public Order find(Integer id) throws ObjectNotFoundException {
		Optional<Order> object = repository.findById(id);
		return object.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Order.class.getName()));

	}
}
