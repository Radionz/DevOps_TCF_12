package fr.unice.polytech.isa.integration;

import api.TCFPublicAPI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import stubs.cart.*;
import stubs.cart.Cookies;
import stubs.customerCare.*;

import javax.xml.ws.BindingProvider;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class IntegrationTest {
    CartWebService cartWs;
    CustomerCareService customerWs;

    @Before
    public void setContext(){
        TCFPublicAPI api= new TCFPublicAPI("localhost", "8080");
        cartWs= api.carts;
        customerWs=api.ccs;
    }

    @Test
    public void normalUsageTest(){
        //The customer lists all the cookies available
        List<stubs.customerCare.Cookies> cookiesAvailable= customerWs.listAllRecipes();
        assertTrue(cookiesAvailable.contains(stubs.customerCare.Cookies.CHOCOLALALA));
        assertTrue(cookiesAvailable.contains(stubs.customerCare.Cookies.DARK_TEMPTATION));
        assertTrue(cookiesAvailable.contains(stubs.customerCare.Cookies.SOO_CHOCOLATE));
        System.out.printf("eeeeeeeee");
       try {
            //He registers
            customerWs.register("Dorian", "30896983");
            //He chooses cookies
            Item i = new Item();
            i.setCookie(stubs.cart.Cookies.CHOCOLALALA);
            i.setQuantity(10);
            cartWs.addItemToCustomerCart("Dorian", i);
            Item i2 = new Item();
            i2.setCookie(stubs.cart.Cookies.SOO_CHOCOLATE);
            i2.setQuantity(50);
            cartWs.addItemToCustomerCart("Dorian", i2);
            //He wants to see what he has chosen
            List<Item> cart  = cartWs.getCustomerCartContents("Dorian");
           //Contains n'appelle pas la bonne version d'equals, on doit donc faire
           int cpt=0;
           for (Item item : cart)
               if ((item.getCookie().equals(i.getCookie()) && item.getQuantity()==i.getQuantity())
                       || (item.getCookie().equals(i2.getCookie()) && item.getQuantity()==i2.getQuantity())) cpt++;
           assertEquals(2, cpt);

            //He took too many SOO_CHOCOLATE, he removes them all
            cartWs.removeItemToCustomerCart("Dorian", i2);
            //He checks if it has been removed
            cart  = cartWs.getCustomerCartContents("Dorian");
           for (Item item: cart)
                assertNotEquals(Cookies.SOO_CHOCOLATE, item.getCookie());
            //He now wants to pay
            String orderID=cartWs.validate("Dorian");
            //After a while, he wants to track his order
            OrderStatus status=customerWs.track(orderID);
            assertEquals(OrderStatus.VALIDATED, status);
        } catch (AlreadyExistingCustomerException_Exception e) {
            e.printStackTrace();
        } catch (UnknownCustomerException_Exception e) {
            e.printStackTrace();
        } catch (PaymentException_Exception e) {
            e.printStackTrace();
        } catch (UnknownOrderId_Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void customerValidateEmptyCart(){
        try {
            //The client registers registers
            customerWs.register("Paul", "10896983");
            //He now wants to pay
            String orderID=cartWs.validate("Paul");
            OrderStatus status=customerWs.track(orderID);
            assertEquals(OrderStatus.VALIDATED, status);
        } catch (AlreadyExistingCustomerException_Exception e) {
            e.printStackTrace();
        } catch (UnknownCustomerException_Exception e) {
            e.printStackTrace();
        } catch (PaymentException_Exception e) {
            e.printStackTrace();
        } catch (UnknownOrderId_Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void customerShowNotExistingCart(){
        try {
            customerWs.register("Jean", "40896983");
            //He wants to see what he has chosen
            List<Item> cart  = cartWs.getCustomerCartContents("Jean");
            assertTrue(cart.isEmpty());
        } catch (AlreadyExistingCustomerException_Exception e) {
            e.printStackTrace();
        } catch (UnknownCustomerException_Exception e) {
            e.printStackTrace();
        }

    }
}