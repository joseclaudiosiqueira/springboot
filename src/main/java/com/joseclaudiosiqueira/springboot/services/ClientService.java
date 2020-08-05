package com.joseclaudiosiqueira.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joseclaudiosiqueira.springboot.domain.Address;
import com.joseclaudiosiqueira.springboot.domain.City;
import com.joseclaudiosiqueira.springboot.domain.Client;
import com.joseclaudiosiqueira.springboot.domain.enums.ClientType;
import com.joseclaudiosiqueira.springboot.dto.DTOClient;
import com.joseclaudiosiqueira.springboot.dto.DTONewClient;
import com.joseclaudiosiqueira.springboot.repositories.AddressRepository;
import com.joseclaudiosiqueira.springboot.repositories.ClientRepository;
import com.joseclaudiosiqueira.springboot.services.exceptions.DataIntegrityException;
import com.joseclaudiosiqueira.springboot.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addressRepository;

	public Client find(Integer id) throws ObjectNotFoundException {
		Optional<Client> object = clientRepository.findById(id);
		return object.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));

	}

	@Transactional
	public Client insert(Client client) {
		client.setId(null);
		client = clientRepository.save(client);
		addressRepository.saveAll(client.getAddresses());
		return client;
	}

	public Client update(Client client) {
		Client updatingClient = find(client.getId());
		updateClient(updatingClient, client);
		return clientRepository.save(updatingClient);
	}

	public void delete(Integer id) {
		find(id);
		try {
			clientRepository.deleteById(id);
		} catch (DataIntegrityViolationException exception) {
			throw new DataIntegrityException("You can't delete Clients that have data associated (e.g. orders).");
		}
	}

	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clientRepository.findAll(pageRequest);
	}

	public Client fromDTO(DTOClient dtoClient) {
		return new Client(dtoClient.getId(), dtoClient.getName(), dtoClient.getEmail(), null, null);
	}

	public Client fromDTO(DTONewClient dtoNewClient) {
		Client client = new Client(null, dtoNewClient.getName(), dtoNewClient.getEmail(), dtoNewClient.getCpfOrCnpj(),
				ClientType.toEnum(dtoNewClient.getType()));
		City city = new City(dtoNewClient.getCityId(), null, null);
		Address address = new Address(null, dtoNewClient.getStreet(), dtoNewClient.getNumber(),
				dtoNewClient.getComplement(), dtoNewClient.getNeighborhood(), dtoNewClient.getZipCode(), client, city);
		client.getAddresses().add(address);
		client.getPhoneNumbers().add(dtoNewClient.getTelephoneNumberOne());
		if (dtoNewClient.getTelephoneNumberTwo() != null) {
			client.getPhoneNumbers().add(dtoNewClient.getTelephoneNumberTwo());
		}
		if (dtoNewClient.getTelephoneNumberThree() != null) {
			client.getPhoneNumbers().add(dtoNewClient.getTelephoneNumberThree());
		}
		return client;
	}

	private void updateClient(Client updatingClient, Client client) {
		updatingClient.setName(client.getName());
		updatingClient.setEmail(client.getEmail());
	}

}