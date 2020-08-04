package com.joseclaudiosiqueira.springboot.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	private List<MessageField> errors = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<MessageField> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new MessageField(fieldName, message));
	}

}
