public class CashPayment implements  Payment {
    @Override
    public PaymentResult pay(double amount) {
        return  PaymentResult.SUCCESS;
    }
}
