import java.util.ArrayList;
import java.util.List;

public class Order {
    private Customer customer;
    private List<OrderItem> items = new ArrayList<>();
    private FulfillmentType fulfillmentType;
    private OrderStatus status;
    private String address;
    private PaymentResult paymentResult;

    Order(Customer customer, FulfillmentType fulfillmentType) {
        this.customer = customer;
        this.fulfillmentType = fulfillmentType;
        this.status = OrderStatus.NEW;
    }

    public void addItem(OrderItem item) {
        if (this.status == OrderStatus.CANCELLED || this.status == OrderStatus.COMPLETED) {
            throw new IllegalStateException("Cannot modify a completed or cancelled order.");
        }
        if (!item.getItem().isAvailable()) {
            throw new IllegalStateException("Cannot add unavailable item.");
        }
        items.add(item);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    void changeStatus(OrderStatus newStatus) {

        if (status == OrderStatus.NEW &&
                (newStatus == OrderStatus.PREPARING ||
                        newStatus == OrderStatus.CANCELLED)) {

            status = newStatus;

        } else if (status == OrderStatus.PREPARING &&
                (newStatus == OrderStatus.READY ||
                        newStatus == OrderStatus.CANCELLED)) {

            status = newStatus;

        } else if (status == OrderStatus.READY &&
                newStatus == OrderStatus.COMPLETED) {

            status = newStatus;

        }else {
            throw new IllegalStateException(
                    "Invalid order status transition."
            );
        }
    }
    void setAddress(String address) {
        if (address == null || address.isBlank() || this.fulfillmentType != FulfillmentType.DELIVERY) {
            throw new IllegalArgumentException("Address is required for delivery orders.");
        }
        this.address = address;
    }

    void validateDelivery() {
        if (fulfillmentType == FulfillmentType.DELIVERY) {
            if (customer.getPhone() == null || customer.getPhone().isBlank()) {
                throw new IllegalArgumentException(
                        "Customer phone is required for delivery orders."
                );
            }
        }
    }

    boolean hasAddress() {
        return address != null && !address.isBlank();
    }

    void confirmOrder() {
        if (items.isEmpty()) {
            throw new IllegalStateException(
                    "Order must contain at least one item."
            );
        }

        if (fulfillmentType == FulfillmentType.DELIVERY) {
            validateDelivery();

            if (!hasAddress()) {
                throw new IllegalStateException(
                        "Delivery address is required."
                );
            }
        }
    }

    public FulfillmentType getFulfillmentType() {
        return fulfillmentType;
    }

    public void setPaymentResult(PaymentResult paymentResult) {
        this.paymentResult = paymentResult;
    }

    public PaymentResult getPaymentResult() {
        return paymentResult;
    }

    public Customer getCustomer() {
        return customer;
    }
}
