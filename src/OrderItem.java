public class OrderItem {
    private MenuItem item;
    private int quantity;

    OrderItem(MenuItem item, int quantity) {

        if (item == null) {
            throw new IllegalArgumentException(
                    "Menu item cannot be null."
            );
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than 0."
            );
        }

        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}