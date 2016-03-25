package fr.unice.polytech.devops;



import fr.unice.polytech.devops.entities.Order;

import javax.ejb.Local;

@Local
public interface OrderProcessing {

	void process(Order order);

}
