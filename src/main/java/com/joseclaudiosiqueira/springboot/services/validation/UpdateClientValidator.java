package com.joseclaudiosiqueira.springboot.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.joseclaudiosiqueira.springboot.domain.Client;
import com.joseclaudiosiqueira.springboot.dto.DTOClient;
import com.joseclaudiosiqueira.springboot.repositories.ClientRepository;
import com.joseclaudiosiqueira.springboot.resources.exceptions.MessageField;

public class UpdateClientValidator implements ConstraintValidator<UpdateClient, DTOClient> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public void initialize(UpdateClient updateClient) {
	}

	@Override
	public boolean isValid(DTOClient dtoClient, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<MessageField> list = new ArrayList<>();

		Client client = clientRepository.findByEmail(dtoClient.getEmail());
		if (client != null && !client.getId().equals(uriId)) {
			list.add(new MessageField("email", "This email has already been taken"));
		}

		for (MessageField e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}