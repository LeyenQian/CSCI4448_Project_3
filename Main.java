import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Customer.Customer;
import Customer.CustomerFactory;
import FoodStore.FoodStore;

public class Main
{    
    public static final int MAX_CUSTOMER_CASUAL   = 12;
    public static final int MAX_CUSTOMER_BUSINESS = 3;
    public static final int MAX_CUSTOMER_CATERING = 3;

    public static int get_random_number(int lower_bound, int upper_bound) {
        return new Random().nextInt(upper_bound - lower_bound + 1) + lower_bound;
    }

    public static void main(String[] args)
    {
        // create customers and shuffle customer order in place
        ArrayList<Customer> customers = new ArrayList<>();
        customers.addAll(CustomerFactory.create(CustomerFactory.TYPE_CASUAL, get_random_number(1, MAX_CUSTOMER_CASUAL)));
        customers.addAll(CustomerFactory.create(CustomerFactory.TYPE_BUSINESS, get_random_number(1, MAX_CUSTOMER_BUSINESS)));
        customers.addAll(CustomerFactory.create(CustomerFactory.TYPE_CATERING, get_random_number(1, MAX_CUSTOMER_CATERING)));
        Collections.shuffle(customers);

        // create food store
        FoodStore food_store = new FoodStore("220 Summit Blvd");
    }
}