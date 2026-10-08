public class MenuItem {
    private String name;
    private double price;
    private boolean available;

    MenuItem(String name, double price, boolean available) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Menu item name cannot be null or blank."
            );
        }

        if (price <= 0) {
            throw new IllegalArgumentException(
                    "Menu item price must be greater than 0."
            );
        }

        this.name = name;
        this.price = price;
        this.available = available;
    }

    public String getName() {
        return this.name;
    }


    public double getPrice() {
        return this.price;
    }

    public boolean isAvailable() {
        return available;
    }
}
