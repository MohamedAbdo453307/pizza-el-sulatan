public class Main {
    public static void main(String[] args) {

        // ==================== SETUP ====================

        Customer customer = new Customer("01096305402");

        Order order = new Order(
                customer,
                FulfillmentType.TAKEAWAY
        );

        MenuItem cola = new MenuItem("Cola", 30, true);
        MenuItem water = new MenuItem("Water", 15, true);
        MenuItem chickenRanch =
                new MenuItem("Chicken Ranch", 160, true);


        // ==================== ORDER ITEMS ====================

        OrderItem colaItem = new OrderItem(cola, 2);
        OrderItem waterItem = new OrderItem(water, 1);
        OrderItem chickenRanchItem =
                new OrderItem(chickenRanch, 1);

        order.addItem(colaItem);
        order.addItem(waterItem);
        order.addItem(chickenRanchItem);


        // ==================== PRICING ====================

        PricingService pricingService = new PricingService();

        double subtotal =
                pricingService.calculateSubtotal(order);

        double discount =
                pricingService.calculateDiscount(order);

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


        // ==================== PAYMENT ====================

        CashPayment cashPayment = new CashPayment();

        PaymentResult cashResult =
                cashPayment.pay(finalTotal);

        order.setPaymentResult(cashResult);

        System.out.println("Cash Payment: " + cashResult);


        CardPayment cardPayment =
                new CardPayment(false);

        PaymentResult cardResult =
                cardPayment.pay(finalTotal);

        System.out.println("Card Payment: " + cardResult);


        // ==================== ORDER STATUS ====================

        System.out.println("Current Status: " + order.getStatus());

        order.changeStatus(OrderStatus.PREPARING);
        System.out.println("Current Status: " + order.getStatus());

        order.changeStatus(OrderStatus.READY);
        System.out.println("Current Status: " + order.getStatus());

        order.changeStatus(OrderStatus.COMPLETED);
        System.out.println("Current Status: " + order.getStatus());


        // ==================== DELIVERY ====================

        Order deliveryOrder =
                new Order(
                        customer,
                        FulfillmentType.DELIVERY
                );

        deliveryOrder.setAddress("Cairo");

        deliveryOrder.addItem(
                new OrderItem(cola, 1)
        );

        deliveryOrder.confirmOrder();

        System.out.println("Delivery order confirmed.");
        DeliveryService deliveryService =
                new InternalDelivery();

        deliveryService.assignDriver(deliveryOrder);

        int estimatedTime =
                deliveryService.estimateDeliveryTime(deliveryOrder);

        System.out.println(
                "Estimated delivery time: "
                        + estimatedTime
                        + " minutes"
        );
        NotificationService notificationService =
                new SMSNotification();

        notificationService.send(
                deliveryOrder,
                "Your order is ready."
        );

        // ==================== EDGE CASE ====================

        Order deliveryOrderWithoutAddress =
                new Order(
                        customer,
                        FulfillmentType.DELIVERY
                );

        deliveryOrderWithoutAddress.addItem(
                new OrderItem(cola, 1)
        );

//        deliveryOrderWithoutAddress.confirmOrder();

// Invalid price
        try {

            new MenuItem(
                    "Invalid Item",
                    -10,
                    true
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid price error: " +
                            e.getMessage()
            );
        }
        // ==================== RECEIPT ====================

        Receipt receipt = new Receipt();

        System.out.println(receipt.generate(order));
    }
}