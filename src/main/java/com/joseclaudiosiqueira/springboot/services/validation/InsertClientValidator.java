package com.joseclaudiosiqueira.springboot.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.joseclaudiosiqueira.springboot.domain.Client;
import com.joseclaudiosiqueira.springboot.domain.enums.ClientType;
import com.joseclaudiosiqueira.springboot.dto.DTONewClient;
import com.joseclaudiosiqueira.springboot.repositories.ClientRepository;
import com.joseclaudiosiqueira.springboot.resources.exceptions.MessageField;
import com.joseclaudiosiqueira.springboot.services.validation.utils.BR;

public class InsertClientValidator implements ConstraintValidator<InsertClient, DTONewClient> {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public void initialize(InsertClient insertClient) {
	}

	@Override
	public boolean isValid(DTONewClient dtoNewClient, ConstraintValidatorContext context) {

		List<MessageField> list = new ArrayList<>();

		if (dtoNewClient.getType().equals(ClientType.NATURALPERSON.getCode())
				&& !BR.isValidCPF(dtoNewClient.getCpfOrCnpj())) {
			list.add(new MessageField("cpfOrCnpj", "Invalid CPF"));
		}
		if (dtoNewClient.getType().equals(ClientType.LEGALENTITY.getCode())
				&& !BR.isValidCNPJ(dtoNewClient.getCpfOrCnpj())) {
			list.add(new MessageField("cpfOrCnpj", "Invalid CNPJ"));
		}

		Client client = clientRepository.findByEmail(dtoNewClient.getEmail());
		if (client != null) {
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