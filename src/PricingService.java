public class PricingService {
    public double calculateSubtotal(Order order) {
        double sum = 0;
        for (OrderItem item : order.getItems()) {
            sum += (item.getQuantity() * item.getItem().getPrice());
        }
        return sum;
    }

    public double calculateDiscount(Order order) {
        double subtotal = calculateSubtotal(order);
        double discount = 0;
        if (subtotal >= 500) {
            discount = subtotal * 0.10;
        }
        return discount;
    }

    public double calculateFulfillmentCharge(Order order) {
        if (order.getFulfillmentType() == FulfillmentType.DELIVERY) {
            return 30;
        }
        return 0;
    }

    public double calculateFinalTotal(Order order) {
        double finalTotal= calculateSubtotal(order) - calculateDiscount(order)+ calculateFulfillmentCharge(order);
       if(finalTotal<0){
           return  0;
       }
       return finalTotal;
    }
}
