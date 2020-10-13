package Customer.Type;

import java.util.Random;

import Customer.Customer;
import FoodStore.FoodStore;

public class Casual extends Customer {

    public boolean buy_rolls(FoodStore food_store) {
        // A casual customer will take any number of available rolls 
        int roll_type = roll_types.get(new Random().nextInt(roll_types.size()));
        if ( !buy_rolls_helper(food_store, roll_type, get_random_number(1, 3), false) ) return false;
        return true;
    }
}
