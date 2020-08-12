package com.joseclaudiosiqueira.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.joseclaudiosiqueira.springboot.domain.Category;
import com.joseclaudiosiqueira.springboot.domain.Product;
import com.joseclaudiosiqueira.springboot.repositories.CategoryRepository;
import com.joseclaudiosiqueira.springboot.repositories.ProductRepository;
import com.joseclaudiosiqueira.springboot.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	public Product find(Integer id) throws ObjectNotFoundException {
		Optional<Product> object = productRepository.findById(id);
		return object.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Product.class.getName()));

	}
	
	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepository.findAllById(ids);
		return productRepository.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
	}
}
