package com.joseclaudiosiqueira.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joseclaudiosiqueira.springboot.domain.Category;
import com.joseclaudiosiqueira.springboot.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public Category search(Integer id) {
		Optional<Category> object = repository.findById(id);
		return object.orElse(null);
		
	}

}
