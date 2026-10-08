public class SMSNotification implements NotificationService {

    @Override
    public void send(Order order, String message) {

        System.out.println(
                "SMS to "
                        + order.getCustomer().getPhone()
                        + ": "
                        + message
        );
    }
}