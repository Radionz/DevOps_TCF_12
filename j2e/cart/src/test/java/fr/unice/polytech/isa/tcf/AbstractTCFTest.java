package fr.unice.polytech.isa.tcf;

import fr.unice.polytech.isa.tcf.components.CartBean;
import fr.unice.polytech.isa.tcf.components.CatalogueBean;
import fr.unice.polytech.isa.tcf.components.CustomerRegistryBean;
import fr.unice.polytech.isa.tcf.components.carts.CartStatefulBean;
import fr.unice.polytech.isa.tcf.components.carts.CartStatelessBean;
import fr.unice.polytech.isa.tcf.entities.Customer;
import fr.unice.polytech.isa.tcf.exceptions.AlreadyExistingCustomerException;
import fr.unice.polytech.isa.tcf.interceptors.Logger;
import fr.unice.polytech.isa.tcf.utils.Database;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import javax.ejb.EJB;

public abstract class AbstractTCFTest {
	
	@EJB
	protected Database memory;

	@Deployment
	public static WebArchive createDeployment() {
		// Building a Web ARchive (WAR) containing the following elements:
		return ShrinkWrap.create(WebArchive.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				// Utils
				.addPackage(Database.class.getPackage())
				// Entities
				.addPackage(Customer.class.getPackage())
				.addPackage(CatalogueBean.class.getPackage())
				.addPackage(CartBean.class.getPackage())
				.addPackage(CartStatefulBean.class.getPackage())
				.addPackage(CartStatelessBean.class.getPackage())
				.addPackage(CartModifier.class.getPackage())
				.addPackage(CustomerRegistryBean.class.getPackage())
				.addPackage(Payment.class.getPackage())
				// Interceptors
				.addPackage(Logger.class.getPackage())
				// Exceptions
				.addPackage(AlreadyExistingCustomerException.class.getPackage());
	}

}
