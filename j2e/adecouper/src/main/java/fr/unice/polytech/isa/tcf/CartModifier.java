package fr.unice.polytech.isa.tcf;


import fr.unice.isa.tcf.entities.Customer;
import fr.unice.isa.tcf.entities.Item;
import fr.unice.isa.tcf.exceptions.PaymentException;

import javax.ejb.Local;
import java.util.Set;

@Local
public interface CartModifier {

	boolean add(Customer c, Item item);

	boolean remove(Customer c, Item item);

	Set<Item> contents(Customer c);

	String validate(Customer c) throws PaymentException;

}
