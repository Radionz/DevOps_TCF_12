package fr.unice.polytech.isa.tcf;


import fr.unice.isa.tcf.entities.Customer;
import fr.unice.isa.tcf.entities.Item;
import fr.unice.isa.tcf.utils.BankAPI;
import fr.unice.isa.tcf.exceptions.PaymentException;


import javax.ejb.Local;
import java.util.Set;

@Local
public interface Payment {

	String payOrder(Customer customer, Set<Item> items) throws PaymentException ;

	void useBankReference(BankAPI bank);
}
