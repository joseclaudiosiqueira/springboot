package com.joseclaudiosiqueira.springboot.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joseclaudiosiqueira.springboot.domain.Product;
import com.joseclaudiosiqueira.springboot.dto.DTOProduct;
import com.joseclaudiosiqueira.springboot.resources.utils.URL;
import com.joseclaudiosiqueira.springboot.services.ProductService;
import com.joseclaudiosiqueira.springboot.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Product object = service.find(id);
		return ResponseEntity.ok(object);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<DTOProduct>> findPage(@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "categories", defaultValue = "") String categories,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		String nameDecoded = URL.decodeParam(name);
		List<Integer> ids = URL.decodeIntList(categories);
		Page<Product> productList = service.search(nameDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<DTOProduct> dtoProductList = productList.map(object -> new DTOProduct(object));
		return ResponseEntity.ok().body(dtoProductList);
	}
}
