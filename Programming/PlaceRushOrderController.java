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
	private CalculateShippingFee fee;
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
		Boolean checkShippingSupport(String customerAddress){

			if (MAX_EXPECTED_DELIVERY_TIME < timeToDelivery(rushMedia) + timeToPickUpOrder(rushMedia)) {
				return false;
			}
			return true;
		}

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
	
	/** method for calculateShippingFee for rushOrder
	 * @param: Order
	 * @Return money for shippingfee
	**/
	public int rushCalculateShippingFee(Order order)  {
        	Random rand = new Random();
        	int fees = (int)( ( (rand.nextFloat()*10)/100 ) * order.getAmount());
        	int additionalFee = (int)( ( (rand.nextFloat()*10)/100 ) * (20 - Integer.parseInt(expectedDeliveryTime)));
        	fees += additionalFee;
        	LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
        	return fees;
   	 }
}
