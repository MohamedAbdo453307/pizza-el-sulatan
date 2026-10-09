public class WhatsAppNotification implements NotificationService{

        @Override
        public void send(Order order, String message) {

            System.out.println(
                    " WhatsApp notification sent to  "
                            + order.getCustomer().getPhone()
                            + ": "
                            + message
            );
        }

}
