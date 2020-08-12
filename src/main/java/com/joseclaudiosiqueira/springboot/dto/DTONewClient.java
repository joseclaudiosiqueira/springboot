package com.joseclaudiosiqueira.springboot.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.joseclaudiosiqueira.springboot.services.validation.InsertClient;

@InsertClient
public class DTONewClient implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Name is a required field")
	@Length(min = 5, max = 120, message = "This field must have between five and one thousand and twenty characters")
	private String name;

	@NotEmpty(message = "Emais is a required field")
	@Email(message = "Invalid email")
	private String email;

	@NotEmpty(message = "CPF / CNPJ is a required field")
	private String cpfOrCnpj;

	private Integer type;

	@NotEmpty(message = "Street is a required field")
	private String street;

	@NotEmpty(message = "Number is a required field")
	private String number;

	private String complement;
	private String neighborhood;

	@NotEmpty(message = "Zipcode is a required field")
	private String zipCode;

	@NotEmpty(message = "Telephone (1) is a required field")
	private String telephoneNumberOne;

	private String telephoneNumberTwo;
	private String telephoneNumberThree;

	private Integer cityId;

	public DTONewClient() {
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

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTelephoneNumberOne() {
		return telephoneNumberOne;
	}

	public void setTelephoneNumberOne(String telephoneNumberOne) {
		this.telephoneNumberOne = telephoneNumberOne;
	}

	public String getTelephoneNumberTwo() {
		return telephoneNumberTwo;
	}

	public void setTelephoneNumberTwo(String telephoneNumberTwo) {
		this.telephoneNumberTwo = telephoneNumberTwo;
	}

	public String getTelephoneNumberThree() {
		return telephoneNumberThree;
	}

	public void setTelephoneNumberThree(String telephoneNumberThree) {
		this.telephoneNumberThree = telephoneNumberThree;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

}
