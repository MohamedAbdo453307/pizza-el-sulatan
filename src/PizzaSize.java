public enum PizzaSize {
    SMALL(0),
    MEDIUM(30),
    LARGE(60);
    private double extraPrice;
    PizzaSize (double extraPrice){
        this.extraPrice =extraPrice;
    }

    public double getExtraPrice() {
        return this.extraPrice;
    }
}
