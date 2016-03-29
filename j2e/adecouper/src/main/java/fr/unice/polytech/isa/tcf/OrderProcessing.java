package fr.unice.polytech.isa.tcf;


import fr.unice.isa.tcf.entities.Order;

import javax.ejb.Local;

@Local
public interface OrderProcessing {

	void process(Order order);

}
