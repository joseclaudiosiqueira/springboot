package com.joseclaudiosiqueira.springboot.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joseclaudiosiqueira.springboot.domain.Category;
import com.joseclaudiosiqueira.springboot.dto.DTOCategory;
import com.joseclaudiosiqueira.springboot.services.CategoryService;
import com.joseclaudiosiqueira.springboot.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Category object = service.find(id);
		return ResponseEntity.ok(object);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody DTOCategory dtoCategory) {
		Category category = service.fromDTO(dtoCategory);
		category = service.insert(category);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody DTOCategory dtoCategory, @PathVariable Integer id) {
		Category category = service.fromDTO(dtoCategory);
		category.setId(id);
		category = service.update(category);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DTOCategory>> findAll() {
		List<Category> categoryList = service.findAll();
		List<DTOCategory> DTOCategoryList = categoryList.stream().map(object -> new DTOCategory(object))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(DTOCategoryList);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<DTOCategory>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name")String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC")String direction) {
		Page<Category> categoryList = service.findPage(page,linesPerPage, orderBy, direction);
		Page<DTOCategory> DTOCategoryList = categoryList.map(object -> new DTOCategory(object));
		return ResponseEntity.ok().body(DTOCategoryList);
	}	
}
