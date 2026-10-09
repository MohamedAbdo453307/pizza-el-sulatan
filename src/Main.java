

public class Main {

    public static void main(String[] args) {

        // Create menu items
        Pizza margherita = new Pizza("Margherita", 100, PizzaSize.SMALL, true);

        Pizza chickenRanch = new Pizza("Chicken Ranch", 160, PizzaSize.SMALL, true);

        MenuItem cola = new MenuItem("Cola", 30, true);
        MenuItem water = new MenuItem("Water", 15, true);
        GarlicBread garlicBread = new GarlicBread("Garlic Bread", 50, true);

        //  Create customer
        Customer customer = new Customer("01012345678");

        // Create order
        Order order = new Order(customer, FulfillmentType.DELIVERY);

        // Add items with quantities
        margherita.addToppings(Topping.EXTRA_CHEESE);

     order.addItem(new OrderItem(margherita, 2));
        order.addItem(new OrderItem(chickenRanch, 1));
        order.addItem(new OrderItem(cola, 2));
        order.addItem(new OrderItem(water, 2));
        order.addItem(new OrderItem(garlicBread, 1));
        // Set delivery address and confirm order
        order.setAddress("Cairo, Egypt");
        order.confirmOrder();

        // Calculate pricing
        PricingService pricingService = new PricingService();

        double subtotal = pricingService.calculateSubtotal(order);
        double discount = pricingService.calculateDiscount(order);
        double deliveryCharge = pricingService.calculateFulfillmentCharge(order);
        double total = pricingService.calculateFinalTotal(order);
  double promotionDiscount =pricingService.calculatePromotionDiscount(order);
        System.out.println("Subtotal: " + subtotal + " EGP");
        System.out.println("Discount: " + discount + " EGP");
        System.out.println("PromotionDiscount: " + promotionDiscount + " EGP");
        System.out.println("Delivery: " + deliveryCharge + " EGP");
        System.out.println("Final total: " + total + " EGP");

        // Start preparing the order
        order.changeStatus(OrderStatus.PREPARING);


        // Payment
//        Payment payment = new CardPayment(true);
//
//        PaymentResult result = payment.pay(total);
//
//        order.setPaymentResult(result);
//
//        if (result == PaymentResult.SUCCESS) {
//            System.out.println("Payment successful.");
//        } else {
//            System.out.println("Payment failed.");
//        }


        Payment payment = new WalletPayment(1000.0);

        PaymentResult result = payment.pay(total);

        order.setPaymentResult(result);

        if (result == PaymentResult.SUCCESS) {
            System.out.println("Wallet payment successful.");
        } else {
            System.out.println("Wallet payment failed.");
        }

        WalletPayment wallet = (WalletPayment) payment;
        System.out.println("Remaining wallet balance: "
                + wallet.getBalance() + " EGP");


        //  Order is ready
        order.changeStatus(OrderStatus.READY);

        //  Notify customer by SMS
       // NotificationService notificationService = new SMSNotification();
     //   notificationService.send(order, "Your order is ready!");
        NotificationService notificationService = new WhatsAppNotification();
        notificationService.send(order, "Your order is ready!");
        // Delivery simulation
//        DeliveryService deliveryService = new InternalDelivery();
//
//        deliveryService.assignDriver(order);
//        deliveryService.estimateDeliveryTime(order);
//        deliveryService.trackDelivery(order);

        DeliveryService deliveryService = new SultanExpress();

        deliveryService.assignDriver(order);

        int estimatedTime =
                deliveryService.estimateDeliveryTime(order);

        System.out.println(
                "Estimated delivery time: "
                        + estimatedTime + " minutes"
        );

        deliveryService.trackDelivery(order);

        // Complete the order after delivery
        order.changeStatus(OrderStatus.COMPLETED);

        // Print receipt
        Receipt receipt = new Receipt();
        System.out.println(receipt.generate(order));


    }
}