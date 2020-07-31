package com.joseclaudiosiqueira.springboot.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joseclaudiosiqueira.springboot.domain.enums.PaymentState;

@Entity
public class BilletPayment extends Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dueDate;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date paidDate;

	public BilletPayment() {
	}

	public BilletPayment(Integer id, PaymentState paymentState, Order order, Date dueDate, Date paidDate) {
		super(id, paymentState, order);
		this.setDueDate(dueDate);
		this.setPaidDate(paidDate);
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

}
