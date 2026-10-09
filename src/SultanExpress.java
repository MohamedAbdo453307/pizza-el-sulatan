
public class SultanExpress implements DeliveryService {

    @Override
    public void assignDriver(Order order) {
        System.out.println(
                "Sultan Express driver assigned to order."
        );
    }

    @Override
    public int estimateDeliveryTime(Order order) {
        return 20;
    }

    @Override
    public void trackDelivery(Order order) {
        System.out.println(
                "Sultan Express: Order is being tracked."
        );
    }
}