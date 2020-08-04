package com.joseclaudiosiqueira.springboot.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.joseclaudiosiqueira.springboot.domain.Category;

public class DTOCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "This is a required field")
	@Length(min = 5, max = 80, message = "This field requires at mininum five characters and a maximum of eighty characters")
	private String name;

	public DTOCategory() {

	}

	public DTOCategory(Category category) {
		id = category.getId();
		name = category.getName();
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

}
