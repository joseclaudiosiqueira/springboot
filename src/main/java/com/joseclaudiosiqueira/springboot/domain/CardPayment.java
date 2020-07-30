package com.joseclaudiosiqueira.springboot.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import com.joseclaudiosiqueira.springboot.domain.enums.PaymentState;

@Entity
public class CardPayment extends Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer installments;

	public CardPayment() {
	}

	public CardPayment(Integer id, PaymentState paymentState, Order order, Integer installments) {
		super(id, paymentState, order);
		this.setInstallments(installments);
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}

}
