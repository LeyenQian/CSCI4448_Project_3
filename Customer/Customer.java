package Customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Food.FoodFactory;
import FoodStore.FoodStore;
import FoodStore.Service.Product;
import FoodStore.Service.ServiceDecorator;
import FoodStore.Service.ServiceFactory;

public abstract class Customer {
    protected String type = this.getClass().getSimpleName();
    protected ArrayList<Product> products = new ArrayList<>();
    protected ArrayList<Integer> roll_types = new ArrayList<>();
    private static final int MAX_SERVICE_SAUCES = 3;
    private static final int MAX_SERVICE_FILLING = 1;
    private static final int MAX_SERVICE_TOOPING = 2;

    public Customer() {
        // add all the roll type into array
        roll_types.add(FoodFactory.TYPE_EGG_ROLL);
        roll_types.add(FoodFactory.TYPE_JELLY_ROLL);
        roll_types.add(FoodFactory.TYPE_PASTRY_ROLL);
        roll_types.add(FoodFactory.TYPE_SAUSAGE_ROLL);
        roll_types.add(FoodFactory.TYPE_SPRING_ROLL);

        // shuffle the order of roll types, so we can simply pop items later.
        Collections.shuffle(roll_types);
    }

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

    public void check_bill(FoodStore food_store, int timestamp) {
        for (Product product : products) {
            System.out.println(String.format("\u001B[31m Customer: %s   \u001B[33m Price: %f   \u001B[32m Description: %s", this.toString(), product.get_price(), product.get_description()));
            
            String name = product instanceof ServiceDecorator ? ((Product)((ServiceDecorator)product).get_original_product()).get_description() : product.get_description();
            food_store.check_bill(this.type, name, product.get_price(), timestamp);
        }
        System.out.println();
    }

    protected boolean buy_rolls_helper(FoodStore food_store, int type, int quantity, boolean strict) {
        // even through there is no enough rolls, still return true, only return false for CODE_NO_INVENTORY
        if ( strict && !food_store.check_availability(type, quantity)) return true;

        try {
            for (int i = 0; i < quantity; i ++) {
                this.products.add(this.buy_services(food_store, food_store.get_product(this.type, type)));
            }
        } catch (Exception e) {
            System.out.println(String.format("\u001B[31m Customer: %s   \u001B[37m %s", this.toString(), e.getMessage()));
            return food_store.get_last_error_code() == FoodStore.CODE_NO_INVENTORY ? false : true;
        }

        return true;
    }

    // each type of customer has different preference on purchasing rolls, they shall be implemented sperately
    public abstract boolean buy_rolls(FoodStore food_store);
}
