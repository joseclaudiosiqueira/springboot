package com.joseclaudiosiqueira.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joseclaudiosiqueira.springboot.domain.Client;
import com.joseclaudiosiqueira.springboot.repositories.ClientRepository;
import com.joseclaudiosiqueira.springboot.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	public Client search(Integer id) throws ObjectNotFoundException {
		Optional<Client> object = repository.findById(id);
		return object.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));

	}

}