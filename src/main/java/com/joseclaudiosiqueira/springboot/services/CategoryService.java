package com.joseclaudiosiqueira.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.joseclaudiosiqueira.springboot.domain.Category;
import com.joseclaudiosiqueira.springboot.dto.DTOCategory;
import com.joseclaudiosiqueira.springboot.repositories.CategoryRepository;
import com.joseclaudiosiqueira.springboot.services.exceptions.DataIntegrityException;
import com.joseclaudiosiqueira.springboot.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public Category find(Integer id) throws ObjectNotFoundException {
		Optional<Category> object = repository.findById(id);
		return object.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));

	}

	public Category insert(Category category) {
		category.setId(null);
		return repository.save(category);
	}

	public Category update(Category category) {
		Category updatingCategory = find(category.getId());
		updateCategory(updatingCategory, category);
		return repository.save(updatingCategory);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException exception) {
			throw new DataIntegrityException("You can't delete categories that have products associated.");
		}
	}

	public List<Category> findAll() {
		return repository.findAll();
	}

	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Category fromDTO(DTOCategory dtoCategory) {
		return new Category(dtoCategory.getId(), dtoCategory.getName());
	}

	private void updateCategory(Category updatingCategory, Category category) {
		updatingCategory.setName(category.getName());
	}
}