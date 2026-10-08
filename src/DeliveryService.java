public interface DeliveryService {

    void assignDriver(Order order);

    int estimateDeliveryTime(Order order);

    void trackDelivery(Order order);
}