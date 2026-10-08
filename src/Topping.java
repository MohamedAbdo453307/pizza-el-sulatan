public enum Topping {
    EXTRA_CHEESE(25),
    OLIVES(15);
    private double extraPrice;

    Topping(double extraPrice) {
        this.extraPrice = extraPrice;
    }

    public double getExtraPrice() {
        return this.extraPrice;
    }
}
