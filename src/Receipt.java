public class Receipt {

    public String generate(Order order) {
        PricingService pricingService = new PricingService();

        double subtotal = pricingService.calculateSubtotal(order);
        double discount = pricingService.calculateDiscount(order);
        double fulfillmentCharge =
                pricingService.calculateFulfillmentCharge(order);
        double finalTotal =
                pricingService.calculateFinalTotal(order);

        StringBuilder receipt = new StringBuilder();

        receipt.append("===== Pizza El Sultan =====\n");
        receipt.append("Order Type: ")
                .append(order.getFulfillmentType())
                .append("\n");

        receipt.append("\nItems:\n");

        for (OrderItem item : order.getItems()) {
            receipt.append("- ")
                    .append(item.getItem().getName())
                    .append(" x ")
                    .append(item.getQuantity())
                    .append("\n");
        }

        receipt.append("\nSubtotal: ")
                .append(subtotal)
                .append("\n");

        receipt.append("Discount: ")
                .append(discount)
                .append("\n");

        receipt.append("Fulfillment Charge: ")
                .append(fulfillmentCharge)
                .append("\n");

        if (order.getPaymentResult() != null) {
            receipt.append("Payment Result: ")
                    .append(order.getPaymentResult())
                    .append("\n");
        }

        receipt.append("Final Total: ")
                .append(finalTotal)
                .append("\n");

        return receipt.toString();
    }
}