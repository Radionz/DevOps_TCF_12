package fr.unice.polytech.devops;



import fr.unice.polytech.devops.entities.Customer;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface CustomerFinder {

	Optional<Customer> findByName(String name);

}

