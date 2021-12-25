package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import entity.cart.Cart;
import entity.cart.CartMedia;
import common.exception.InvalidDeliveryInfoException;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderMedia;
import views.screen.popup.PopupScreen;

/**
 * This class controls the flow of place order usecase in our AIMS project
 * @author nguyenlm
 */
public class PlaceOrderController extends BaseController{

    
    
    private CalculateShippingFee fee;
    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

    /**
     * This method checks the avalibility of product when user click PlaceOrder button
     * @throws SQLException
     */
    public void placeOrder() throws SQLException{
        Cart.getCart().checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    public Order createOrder() throws SQLException{
        Order order = new Order();
        for (Object object : Cart.getCart().getListMedia()) {
            CartMedia cartMedia = (CartMedia) object;
            OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(), 
                                                   cartMedia.getQuantity(), 
                                                   cartMedia.getPrice());    
            order.getlstOrderMedia().add(orderMedia);
        }
        return order;
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public void processDeliveryInfo(HashMap info) throws InterruptedException, IOException{
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        validateDeliveryInfo(info);
    }
    
    /**
   * The method validates the info
   * @param info
   * @throws InterruptedException
   * @throws IOException
   */
    public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException{
    	
    }
    
    public boolean validatePhoneNumber(String phoneNumber) {
    	// TODO: your work
    	
    	// Kiểm tra xem kí tự đầu và kí tự cuối có dấu cách không?.
    	if(Character.isWhitespace(phoneNumber.charAt(0)) ||Character.isWhitespace(phoneNumber.charAt(phoneNumber.length()-1))) {
    		phoneNumber = phoneNumber.strip();
    	}
    	
    	// Kiểm tra độ dài số điện thoại có khác 10 hay không?.
    	if (phoneNumber.length() !=10) {
    	return false;
    	}
    	
    	
    	
    	if(!phoneNumber.startsWith("0")||!phoneNumber.startsWith("")) return false;
    	//ktra so chi chua cac number
    	try {
    		Integer.parseInt(phoneNumber);
    	} catch (NumberFormatException s) {
    		return false;
    	}
    	return true;
    }
    
    public boolean validateName(String name) {
    	// TODO: your work
 
    	//Kiem tra NULL or EMPTY
    	if (name == ""||name.length()==0) return false;
    	// Check if it have digit
    	for (int i =0; i < name.length(); i++) {
    		 Boolean flag = Character.isDigit(name.charAt(i));
    		 if (flag == true) return false;
    		}
    	//Check if has special character
    	for (int i =0; i < name.length(); i++) {
    		if (!Character.isDigit(name.charAt(i))
                 && !Character.isLetter(name.charAt(i))
                 && !Character.isWhitespace(name.charAt(i))) {
   
                 return false;
             }
         }
    	
    	return true;
    	}
    	
    
    
    public boolean validateAddress(String address) {
    	// TODO: your work
    	//Kiem tra NULL or EMPTY
    	if (address == ""||address.length()==0) return false;
    	
    	//Kiem tra co chua ki tu dac biet hay khong
    	for (int i =0; i < address.length(); i++) {
    		if (!Character.isDigit(address.charAt(i))
                 && !Character.isLetter(address.charAt(i))
                 && !Character.isWhitespace(address.charAt(i))) {
   
                 return false;
             }
    	}
    	return true;
    }
    
    /**
    * method for calculateShippingFee
    * @Param: order
    **/
    fee = new NormalCalculateShippingFee();
    fee.calculateShippingFee(Order order);
}
