public class Main {
    public static void main(String[] args) {


        Customer customer = new Customer("01096305402");

        Order order = new Order(customer, FulfillmentType.TAKEAWAY);

        MenuItem cola = new MenuItem("Cola", 30);
        MenuItem water = new MenuItem("Water", 15);
        MenuItem chickenRanch = new MenuItem("Chicken Ranch", 160);


        OrderItem colaItem = new OrderItem(cola, 2);
        OrderItem waterItem = new OrderItem(water, 1);
        OrderItem chickenRanchItem = new OrderItem(chickenRanch, 1);


        order.addItem(colaItem);
        order.addItem(waterItem);
        order.addItem(chickenRanchItem);


        PricingService pricingService = new PricingService();

        double subtotal = pricingService.calculateSubtotal(order);
        double discount = pricingService.calculateDiscount(order);
        double fulfillmentCharge =
                pricingService.calculateFulfillmentCharge(order);
        double finalTotal =
                pricingService.calculateFinalTotal(order);

        System.out.println("===== Pizza El Sultan =====");
        System.out.println("Order Type: " + order.getFulfillmentType());
        System.out.println("Subtotal: " + subtotal);
        System.out.println("Discount: " + discount);
        System.out.println("Fulfillment Charge: " + fulfillmentCharge);
        System.out.println("Final Total: " + finalTotal);
    }
}