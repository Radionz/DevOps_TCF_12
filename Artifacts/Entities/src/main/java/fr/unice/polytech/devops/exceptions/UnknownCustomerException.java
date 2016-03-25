package fr.unice.polytech.devops.exceptions;

import java.io.Serializable;


public class UnknownCustomerException extends Exception implements Serializable {

	private String name;

	public UnknownCustomerException(String name) {
		name = name;
	}


	public UnknownCustomerException() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
