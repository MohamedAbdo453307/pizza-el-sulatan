public class InternalDelivery implements DeliveryService {

    @Override
    public void assignDriver(Order order) {

        System.out.println(
                "Internal driver assigned to order."
        );
    }

    @Override
    public int estimateDeliveryTime(Order order) {

        return 30;
    }

    @Override
    public void trackDelivery(Order order) {

        System.out.println(
                "Internal delivery: Order is being tracked."
        );
    }
}