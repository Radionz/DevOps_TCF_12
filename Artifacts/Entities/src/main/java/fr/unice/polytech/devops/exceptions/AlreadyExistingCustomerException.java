package fr.unice.polytech.devops.exceptions;

import java.io.Serializable;


public class AlreadyExistingCustomerException extends Exception implements Serializable {

	private String conflictingName;

	public AlreadyExistingCustomerException(String name) {
		conflictingName = name;
	}


	public AlreadyExistingCustomerException() {
	}

	public String getConflictingName() {
		return conflictingName;
	}

	public void setConflictingName(String conflictingName) {
		this.conflictingName = conflictingName;
	}
}
