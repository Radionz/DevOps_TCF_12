package fr.unice.polytech.isa.integration;


/*

@RunWith(Arquillian.class)
public class PaymentIntegrationTest extends AbstractTCFTest {

	@EJB private Payment cashier;
	@EJB private CustomerFinder finder;
	@EJB private CustomerRegistration registration;

	private Set<Item> items;

	@Before
	public void setUpContext() {
		memory.flush();
		items = new HashSet<>();
		items.add(new Item(Cookies.CHOCOLALALA, 3));
		items.add(new Item(Cookies.DARK_TEMPTATION, 2));
	}

	@Test
	public void integrationBetweenCustomersAndOrders() throws Exception {
		registration.register("john", "1234-896983");
		Customer retrieved = finder.findByName("john").get();
		assertTrue(retrieved.getOrders().isEmpty());
		String id = cashier.payOrder(retrieved, items);
		Order order = memory.getOrders().get(id);
		assertTrue(retrieved.getOrders().contains(order));
	}

}
		*/
