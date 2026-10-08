public class CardPayment implements  Payment{
 private  boolean authorized;

    public CardPayment(boolean authorized) {
        this.authorized = authorized;
    }

    @Override
    public PaymentResult pay(double amount) {
        return this.authorized?PaymentResult.SUCCESS:PaymentResult.FAILED;
    }
}
