package fr.unice.polytech.devops;

import fr.unice.polytech.devops.entities.Customer;
import fr.unice.polytech.devops.entities.Item;
import fr.unice.polytech.devops.exceptions.PaymentException;

import javax.ejb.Local;
import java.util.Set;

@Local
public interface CartModifier {

	boolean add(Customer c, Item item);

	boolean remove(Customer c, Item item);

	Set<Item> contents(Customer c);

	String validate(Customer c) throws PaymentException;

}
