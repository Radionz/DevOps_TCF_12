package fr.unice.polytech.isa.tcf;


import fr.unice.polytech.isa.tcf.entities.Cookies;
import fr.unice.polytech.isa.tcf.entities.Customer;
import fr.unice.polytech.isa.tcf.entities.Item;
import fr.unice.polytech.isa.tcf.entities.Order;
import fr.unice.polytech.isa.tcf.exceptions.PaymentException;
import fr.unice.polytech.isa.tcf.tests.AbstractTCFTest;
import fr.unice.polytech.isa.tcf.utils.BankAPI;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;

import javax.ejb.EJB;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Arquillian.class)
public class CashierTest extends AbstractTCFTest {

	@EJB private Payment cashier;

	// Test context
	private Set<Item> items;
	Customer john;
	Customer pat;

	@Before
	public void setUpContext() throws Exception {
		memory.flush();
		items = new HashSet<>();
		items.add(new Item(Cookies.CHOCOLALALA, 3));
		items.add(new Item(Cookies.DARK_TEMPTATION, 2));
		// Customers
		john = new Customer("john", "1234-896983");  // ends with the secret YES Card number
		pat  = new Customer("pat", "1234-567890");   // should be rejected by the payment service
		// Mocking the external partner
		BankAPI mocked = Mockito.mock(BankAPI.class);
		cashier.useBankReference(mocked);
		Mockito.when(mocked.performPayment(Matchers.eq(john), Matchers.anyDouble())).thenReturn(true);
		Mockito.when(mocked.performPayment(Matchers.eq(pat),  Matchers.anyDouble())).thenReturn(false);
	}

	@Test
	public void processToPayment() throws Exception {
		// paying order
 		String id = cashier.payOrder(john, items);

		// memory contents from the Order point of view
		Order order = memory.getOrders().get(id);
		Assert.assertNotNull(order);
		Assert.assertEquals(john, order.getCustomer());
		Assert.assertEquals(items, order.getItems());
		double price = (3 * Cookies.CHOCOLALALA.getPrice()) + (2 * Cookies.DARK_TEMPTATION.getPrice());
		Assert.assertEquals(price, order.getPrice(), 0.0);
	}

	@Test(expected = PaymentException.class)
	public void identifyPaymentError() throws Exception {
		cashier.payOrder(pat, items);
	}

}
