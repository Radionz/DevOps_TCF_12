package fr.unice.polytech.devops.components;


import fr.unice.polytech.devops.CartModifier;
import fr.unice.polytech.devops.Payment;
import fr.unice.polytech.devops.entities.Customer;
import fr.unice.polytech.devops.entities.Item;
import fr.unice.polytech.devops.exceptions.PaymentException;
import fr.unice.polytech.devops.interceptors.CartCounter;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import java.util.Optional;
import java.util.Set;

public abstract class CartBean implements CartModifier {

	@EJB
	protected Payment cashier;

	@Override
	@Interceptors({CartCounter.class})
	public String validate(Customer c) throws PaymentException {
		return cashier.payOrder(c, contents(c));
	}

	@Override
	public final boolean remove(Customer c, Item item) {
		return add(c, new Item(item.getCookie(), -item.getQuantity()));
	}

	/**
	 * Protected method to update the cart of a given customer, shared by both stateful and stateless beans
	 */
	protected Set<Item> updateCart(Customer c, Item item) {
		Set<Item> items = contents(c);
		Optional<Item> existing = items.stream().filter(e -> e.getCookie().equals(item.getCookie())).findFirst();
		if(existing.isPresent()) {
			items.remove(existing.get());
			Item toAdd = new Item(item.getCookie(), item.getQuantity() + existing.get().getQuantity());
			if(toAdd.getQuantity() > 0) { items.add(toAdd); }
		} else {
			items.add(item);
		}
		return items;
	}
}
