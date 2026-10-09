import java.util.ArrayList;
import java.util.List;

public class Pizza extends MenuItem {
    private PizzaSize size;
    private List<Topping> toppings = new ArrayList<>();

    public Pizza(String name, double price, PizzaSize size,boolean available) {
        super(name, price,available);
        if (size == null) {
            throw new IllegalArgumentException(
                    "Pizza size cannot be null."
            );
        }
        this.size = size;
    }

    public void addToppings(Topping topping) {
        toppings.add(topping);
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    @Override
    public double getPrice() {
         double price = super.getPrice();
        price+= size.getExtraPrice();
        for (Topping topping:toppings){
            price+=topping.getExtraPrice();
        }
        return  price;
    }

    public PizzaSize getSize() {
        return size;
    }
}
