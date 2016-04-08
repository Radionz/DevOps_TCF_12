package integration;


import arquillian.AbstractTCFTest;
import fr.unice.polytech.isa.tcf.CartModifier;
import fr.unice.polytech.isa.tcf.CustomerFinder;
import fr.unice.polytech.isa.tcf.CustomerRegistration;
import fr.unice.polytech.isa.tcf.entities.Cookies;
import fr.unice.polytech.isa.tcf.entities.Customer;

import fr.unice.polytech.isa.tcf.entities.Item;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class RegistrationIntegrationTest extends AbstractTCFTest {

	@EJB private CustomerFinder finder;
	@EJB private CustomerRegistration registration;
	@EJB private CartModifier cartModifier;

	private Item item;

	@Before
	public void setUpContext() {
		memory.flush();
		item = new Item(Cookies.SOO_CHOCOLATE, 1);
	}

	@Test
	public void integrationBetweenCustomerAndDatabase() throws Exception {
		// == Create customer with customer registration
		registration.register("dorian", "1234-896983");

		// Check integration with customer finder
		Customer retrieved = finder.findByName("dorian").get();
		assertEquals("dorian", retrieved.getName());
		assertEquals("1234-896983", retrieved.getCreditCard());

		// Check integration with database (really added)
		Customer retrievedDB = memory.getCustomers().get("dorian");
		assertEquals("dorian", retrievedDB.getName());
		assertEquals("1234-896983", retrievedDB.getCreditCard());
	}

	@Test
	public void integrationBetweenCartModifierAndDatabase() throws Exception {
		registration.register("dorian", "1234-896983");
		Customer retrieved = finder.findByName("dorian").get();

		// == Add an item in the cart
		cartModifier.add(retrieved, item);

		// Check integration with database (really added)
		assertTrue(memory.getCarts().get(retrieved).contains(item));

		// == Remove an item from the cart
		cartModifier.remove(retrieved, item);

		// Check integration with database (really removed)
		assertFalse(memory.getCarts().get(retrieved).contains(item));
	}
}
