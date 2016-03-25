package fr.unice.polytech.devops;

import fr.unice.polytech.devops.entities.OrderStatus;
import fr.unice.polytech.devops.exceptions.UnknownOrderId;

import javax.ejb.Local;

@Local
public interface Tracker {

	OrderStatus status(String orderId) throws UnknownOrderId;

}
