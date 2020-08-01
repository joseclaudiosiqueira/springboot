package com.joseclaudiosiqueira.springboot.dto;

import java.io.Serializable;

import com.joseclaudiosiqueira.springboot.domain.Category;

public class DTOCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
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
