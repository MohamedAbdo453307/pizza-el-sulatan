
public class WalletPayment implements Payment {

    private double balance;

    public WalletPayment(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException(
                    "Wallet balance cannot be negative."
            );
        }

        this.balance = balance;
    }

    @Override
    public PaymentResult pay(double amount) {
        if (amount <= 0 || amount > balance) {
            return PaymentResult.FAILED;
        }

        balance -= amount;
        return PaymentResult.SUCCESS;
    }

    public double getBalance() {
        return balance;
    }
}