package controller;

public class RushClaculateShippingFee extends CalculateShippingFee {
   public int rushCalculateShippingFee(Order order) {
        Random rand = new Random();
        int fees = (int)( ( (rand.nextFloat()*10)/100 ) * order.getAmount());
        int additionalFee = (int)( ( (rand.nextFloat()*10)/100 ) * (20 - Integer.parseInt(expectedDeliveryTime)));
        fees += additionalFee;
        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
        return fees;
    }
}
