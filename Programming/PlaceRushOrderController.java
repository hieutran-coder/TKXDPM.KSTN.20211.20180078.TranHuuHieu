package controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import entity.media.Media;
import entity.order.Order;
import entity.order.OrderMedia;

/**
 * lop nay phuc vu cho vien dieu khien luong giao hang nhanh
 * @author TranHuuhieu
 *
 */
public class PlaceRushOrderController extends PlaceOrderController{
	private Order usualMedia;
	private Order rushMedia;
	
	private static Logger LOGGER = utils.Utils.getLogger(PlaceRushOrderController.class.getName());
	 public static final int MAX_EXPECTED_DELIVERY_TIME = 7;
	/**
	 * 
	 */
	void placeRushOrder(){
	}
	
	/**
	 * 
	 */
	void checkRushOrderSupport(){

	}
	
	/**
	 * tinh thoi gian van chuyen tu kho hang den cho khach hang
	 * @param order
	 * @return
	 */
	float timeToDelivery(Order order) {
		return 0;
		
	}
	/**
	 * tinh thoi gian lay hang tu kho hang 
	 * @param order
	 * @return
	 */
	float timeToPickUpOrder(Order order) {
		return 0;
		
	}
	/**
	 * kiem tra xem co shipping khong?
	 * @param customerAddress
	 * @return
	 */
	Boolean checkShippingSupport(String customerAddress){
		
		if (MAX_EXPECTED_DELIVERY_TIME < timeToDelivery(rushMedia) + timeToPickUpOrder(rushMedia)) {
			return false;
		}
		return true;
		
		
	}
	
	/**
	 * @param OrderMedia
	 * 
	 * @return
	 */
	Boolean notRushOrder(OrderMedia media) {
		return true;
		}
	/**
	 * @param order
	 */
	void arrangeRushOrder(Order order){
		for (int i =0; i< order.getlstOrderMedia().size(); i++) {
			if (notRushOrder((OrderMedia) order.getlstOrderMedia().get(i)))
			{
				usualMedia.addOrderMedia((OrderMedia) order.getlstOrderMedia().get(i));	
				}

		}
	}
	
	/**
	 * @param expectedDeliveryTime: Thơi gian du kien giao hang ma khach hang mong muon
	 * @return
	 */
	Boolean  validateExpectedDeliveryTime(int expectedDeliveryTime) {
		if( expectedDeliveryTime > 7||expectedDeliveryTime < 1) return false;
		
		return true ;
			
	}
	
	
	/**
	 * @param order
	 * @param expectedDeliveryTime: Thoi gian du kien giao hang ma khach hang mong muon
	 * @return
	 */
	public int calculateShippingFee(Order order, int expectedDeliveryTime){
        Random rand = new Random();
        
       
        int fees = (int)( ( (rand.nextFloat()*10)/100 ) * order.getAmount());
        // cost 10k /rushPrudct
        int addtionalFees = expectedDeliveryTime * order.getAmount()*10;
        fees += addtionalFees;
        
        
        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
        return fees;
    }
}
