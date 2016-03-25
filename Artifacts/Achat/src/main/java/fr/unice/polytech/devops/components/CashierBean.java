package fr.unice.polytech.devops.components;

import fr.unice.polytech.devops.OrderProcessing;
import fr.unice.polytech.devops.Payment;
import fr.unice.polytech.devops.entities.Customer;
import fr.unice.polytech.devops.entities.Item;
import fr.unice.polytech.devops.entities.Order;
import fr.unice.polytech.devops.exceptions.ExternalPartnerException;
import fr.unice.polytech.devops.exceptions.PaymentException;
import fr.unice.polytech.devops.utils.BankAPI;
import fr.unice.polytech.devops.utils.Database;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

@Stateless
public class CashierBean implements Payment {

	@EJB private OrderProcessing kitchen;
	@EJB private Database memory;

	private BankAPI bank;
	public void useBankReference(BankAPI bank) { this.bank = bank; }

	@Override
	public String payOrder(Customer customer, Set<Item> items) throws PaymentException {

		Order order = new Order(customer, items);
		double price = order.getPrice();

		boolean status = false;
		try { status = bank.performPayment(customer, price); }
		catch (ExternalPartnerException e) {
			throw new PaymentException(customer.getName(), price);
		}

		if (!status) {
			throw new PaymentException(customer.getName(), price);
		}

		customer.add(order);
		memory.getOrders().put(order.getId(),order);
		kitchen.process(order);

		return order.getId();
	}

	@PostConstruct
	private void initializeRestPartnership() throws IOException {
		Properties prop = new Properties();
		prop.load(this.getClass().getResourceAsStream("/bank.properties"));
		bank = new BankAPI(	prop.getProperty("bankHostName"),
							prop.getProperty("bankPortNumber"));
	}

}
