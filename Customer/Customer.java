package Customer;

import java.util.ArrayList;
import java.util.Random;

import FoodStore.FoodStore;
import FoodStore.Service.Product;
import FoodStore.Service.ServiceFactory;

public abstract class Customer {
    protected String type = this.getClass().getSimpleName();
    protected ArrayList<Product> products = new ArrayList<>();
    private static final int MAX_SERVICE_SAUCES = 3;
    private static final int MAX_SERVICE_FILLING = 1;
    private static final int MAX_SERVICE_TOOPING = 2;

    public String toString() {
        return this.hashCode() + " <" + this.type + "> ";
    }

    public int get_random_number(int lower_bound, int upper_bound) {
        return new Random().nextInt(upper_bound - lower_bound + 1) + lower_bound;
    }
    
    public Product buy_services(FoodStore food_store, Product item) {
        int sauces = get_random_number(0, MAX_SERVICE_SAUCES);
        int fillings = get_random_number(0, MAX_SERVICE_FILLING);
        int toppings = get_random_number(0, MAX_SERVICE_TOOPING);

        for (int i = 0; i < sauces; i ++) item = food_store.get_service(ServiceFactory.TYPE_SAUCE, item);
        for (int i = 0; i < fillings; i ++) item = food_store.get_service(ServiceFactory.TYPE_FILLING, item);
        for (int i = 0; i < toppings; i ++) item = food_store.get_service(ServiceFactory.TYPE_TOPPING, item);
        
        return item;
    }

    public void check_bill() {
        for (Product item : products) {
            System.out.println(String.format("Customer: %s;   Description: %s;   Price: %f", this.toString(), item.get_description(), item.get_price()));
        }
    }

    // each type of customer has different preference on purchasing rolls, they shall be implemented sperately
    public abstract boolean buy_rolls(FoodStore food_store);
}
