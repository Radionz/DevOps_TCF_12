package fr.unice.polytech.isa.tcf;

import fr.unice.isa.tcf.entities.OrderStatus;
import fr.unice.isa.tcf.exceptions.UnknownOrderId;

import javax.ejb.Local;

@Local
public interface Tracker {

	OrderStatus status(String orderId) throws UnknownOrderId;

}
