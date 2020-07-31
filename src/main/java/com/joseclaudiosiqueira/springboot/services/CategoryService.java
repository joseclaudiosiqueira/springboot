package com.joseclaudiosiqueira.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joseclaudiosiqueira.springboot.domain.Category;
import com.joseclaudiosiqueira.springboot.repositories.CategoryRepository;
import com.joseclaudiosiqueira.springboot.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public Category search(Integer id) throws ObjectNotFoundException {
		Optional<Category> object = repository.findById(id);
		return object.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));

	}

	public Category insert(Category category) {
		category.setId(null);
		return repository.save(category);
	}

}