package com.joseclaudiosiqueira.springboot.resources;

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

import com.joseclaudiosiqueira.springboot.domain.Client;
import com.joseclaudiosiqueira.springboot.dto.DTOClient;
import com.joseclaudiosiqueira.springboot.services.ClientService;
import com.joseclaudiosiqueira.springboot.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

	@Autowired
	private ClientService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Client> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Client object = service.find(id);
		return ResponseEntity.ok(object);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody DTOClient dtoClient, @PathVariable Integer id) {
		Client category = service.fromDTO(dtoClient);
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
	public ResponseEntity<List<DTOClient>> findAll() {
		List<Client> categoryList = service.findAll();
		List<DTOClient> DTOClientList = categoryList.stream().map(object -> new DTOClient(object))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(DTOClientList);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<DTOClient>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name")String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC")String direction) {
		Page<Client> categoryList = service.findPage(page,linesPerPage, orderBy, direction);
		Page<DTOClient> DTOClientList = categoryList.map(object -> new DTOClient(object));
		return ResponseEntity.ok().body(DTOClientList);
	}	
	
}
