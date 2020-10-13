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

    public static ArrayList<Customer> generate_customers() {
        ArrayList<Customer> customers = new ArrayList<>();
        customers.addAll(CustomerFactory.create(CustomerFactory.TYPE_CASUAL, get_random_number(1, MAX_CUSTOMER_CASUAL)));
        customers.addAll(CustomerFactory.create(CustomerFactory.TYPE_BUSINESS, get_random_number(1, MAX_CUSTOMER_BUSINESS)));
        customers.addAll(CustomerFactory.create(CustomerFactory.TYPE_CATERING, get_random_number(1, MAX_CUSTOMER_CATERING)));
        Collections.shuffle(customers);
        return customers;
    }

    public static void main(String[] args)
    {
        // create food store
        FoodStore food_store = new FoodStore("220 Summit Blvd");
        int total_days = 2;

        for (int day = 1; day <= total_days; day ++) {
            // create customers and shuffle customer order in place
            ArrayList<Customer> customers = generate_customers();

            System.out.println(String.format("\n\u001B[37m----------------------------------------Food Stroe at <%s> on <Day %02d>----------------------------------------", food_store.get_address(), day));
            food_store.show_inventory("********************************* Day Begin Inventory *********************************");
            for (Customer customer : customers) {
                boolean result = customer.buy_rolls(food_store);
                customer.check_bill(food_store, day);
                if (!result) break;
            }
            food_store.summary_day(day);
            food_store.show_inventory("********************************* Day  End  Inventory *********************************");
            food_store.check_inventory();
        }

        System.out.println(String.format("\n\u001B[37m---------------------------------------- Summary for last %02d Days ----------------------------------------", total_days));
        food_store.summary_all(2);
    }
}