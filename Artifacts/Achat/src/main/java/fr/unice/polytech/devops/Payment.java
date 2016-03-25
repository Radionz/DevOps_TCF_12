package fr.unice.polytech.devops;


import fr.unice.polytech.devops.entities.Customer;
import fr.unice.polytech.devops.entities.Item;
import fr.unice.polytech.devops.exceptions.PaymentException;
import fr.unice.polytech.devops.utils.BankAPI;

import javax.ejb.Local;
import java.util.Set;

@Local
public interface Payment {

	String payOrder(Customer customer, Set<Item> items) throws PaymentException ;

	void useBankReference(BankAPI bank);
}
