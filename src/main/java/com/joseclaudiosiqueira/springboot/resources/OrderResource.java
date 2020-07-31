package com.joseclaudiosiqueira.springboot.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joseclaudiosiqueira.springboot.domain.Order;
import com.joseclaudiosiqueira.springboot.services.OrderService;
import com.joseclaudiosiqueira.springboot.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {
	
	@Autowired
	private OrderService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> search(@PathVariable Integer id) throws ObjectNotFoundException {
		Order object = service.search(id);
		return ResponseEntity.ok(object);
	}
}
