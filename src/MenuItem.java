public class MenuItem {
    private String name;
    private double price;

    MenuItem(String name, double price) {
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
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }
}