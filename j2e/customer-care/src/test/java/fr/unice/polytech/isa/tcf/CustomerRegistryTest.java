package fr.unice.polytech.isa.tcf;

import fr.unice.polytech.isa.tcf.entities.Customer;
import fr.unice.polytech.isa.tcf.exceptions.AlreadyExistingCustomerException;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.Optional;

@RunWith(Arquillian.class)
public class CustomerRegistryTest extends AbstractTCFTest {

	@EJB private CustomerRegistration registry;
	@EJB private CustomerFinder finder;

	@Before
	public void setUpContext() throws Exception { memory.flush(); }

	@Test
	public void unknownCustomer() {
		Assert.assertFalse(finder.findByName("John").isPresent());
	}

	@Test
	public void registerCustomer() throws Exception {
		String name = "John";
		String creditCard = "credit card number";
		registry.register(name, creditCard);
		Optional<Customer> customer = finder.findByName(name); // A debug
		Assert.assertTrue(customer.isPresent());
		Customer john = customer.get();
		Assert.assertEquals(name, john.getName());
		Assert.assertEquals(creditCard, john.getCreditCard());
	}


	@Test(expected = AlreadyExistingCustomerException.class)
	public void cannotRegisterTwice() throws Exception {
		String name = "John";
		String creditCard = "credit card number";
		registry.register(name, creditCard);
		registry.register(name, creditCard);
	}

}
