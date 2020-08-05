package com.joseclaudiosiqueira.springboot.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.joseclaudiosiqueira.springboot.domain.Client;

public class DTOClient implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="The Client's name is a required field")
	@Length(min=5, max=120, message="The lenght needs to b between 5 and 120 characters")
	private String name;
	
	@NotEmpty(message="The Client's email is a required field")
	@Email(message="Invalid email")
	private String email;
	
	public DTOClient() {
		
	}
	
	public DTOClient(Client client) {
		id = client.getId();
		name = client.getName();
		email = client.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
