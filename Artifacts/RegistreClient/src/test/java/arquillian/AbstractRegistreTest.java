package arquillian;


import fr.unice.polytech.devops.entities.Customer;
import fr.unice.polytech.devops.utils.Database;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;


import javax.ejb.EJB;

public abstract class AbstractRegistreTest {

/**
	@EJB
	protected Database memory;

	@Deployment
	public static WebArchive createDeployment() {
		// Building a Web ARchive (WAR) containing the following elements:
		return ShrinkWrap.create(WebArchive.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				// Utils
				//.addPackage(Database.class.getPackage())
				// Entities
				.addPackage(Customer.class.getPackage());
				// Components Interfaces
				//.addPackage(CartModifier.class.getPackage())
				// Cart components
				//.addPackage(CartStatefulBean.class.getPackage())
				// Interceptors
				//.addPackage(Logger.class.getPackage())
				// Exceptions
				//.addPackage(AlreadyExistingCustomerException.class.getPackage())
				// Components implementations
				//.addPackage(CartBean.class.getPackage());

	}
**/
}
