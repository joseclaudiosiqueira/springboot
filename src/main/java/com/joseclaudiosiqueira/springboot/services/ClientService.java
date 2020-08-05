package com.joseclaudiosiqueira.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.joseclaudiosiqueira.springboot.domain.Client;
import com.joseclaudiosiqueira.springboot.dto.DTOClient;
import com.joseclaudiosiqueira.springboot.repositories.ClientRepository;
import com.joseclaudiosiqueira.springboot.services.exceptions.DataIntegrityException;
import com.joseclaudiosiqueira.springboot.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	public Client find(Integer id) throws ObjectNotFoundException {
		Optional<Client> object = repository.findById(id);
		return object.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));

	}

	public Client update(Client client) {
		Client updatingClient = find(client.getId());
		updateClient(updatingClient, client);
		return repository.save(updatingClient);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException exception) {
			throw new DataIntegrityException("You can't delete Clients that have data associated (e.g. orders).");
		}
	}

	public List<Client> findAll() {
		return repository.findAll();
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Client fromDTO(DTOClient dtoClient) {
		return new Client(dtoClient.getId(), dtoClient.getName(), dtoClient.getEmail(), null, null);
	}

	private void updateClient(Client updatingClient, Client client) {
		updatingClient.setName(client.getName());
		updatingClient.setEmail(client.getEmail());
	}

}