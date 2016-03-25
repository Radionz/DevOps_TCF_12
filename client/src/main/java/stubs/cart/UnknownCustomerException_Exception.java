
package stubs.cart;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "UnknownCustomerException", targetNamespace = "http://webservice.tcf.isa.polytech.unice.fr/")
public class UnknownCustomerException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private UnknownCustomerException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public UnknownCustomerException_Exception(String message, UnknownCustomerException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public UnknownCustomerException_Exception(String message, UnknownCustomerException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: stubs.cart.UnknownCustomerException
     */
    public UnknownCustomerException getFaultInfo() {
        return faultInfo;
    }

}
